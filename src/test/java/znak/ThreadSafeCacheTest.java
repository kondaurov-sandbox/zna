package znak;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeCacheTest {

    Executor executor = Executors.newFixedThreadPool(10);

    CompletableFuture<String> exec(
        ThreadSafeCache<String, String> cache,
        String key,
        Integer sleep,
        String val
    ) {
        return CompletableFuture.supplyAsync(() -> cache.compute(
            key,
            (k) -> {
                try {
                    Thread.sleep(sleep);
                    return String.format("value for key (%s): %s", k, val);
                } catch (Exception e) {
                    return null;
                }
            }
        ), executor).thenComposeAsync(v -> v);
    }

    @Test
    void case1() throws ExecutionException, InterruptedException {

        var cache = new ThreadSafeCache<String, String>();

        CompletableFuture.allOf(
            exec(cache, "key1",1000, "val1"),
            exec(cache, "key1", 500, "val2").thenAccept((v) -> System.out.println("got result " + v)),
            exec(cache, "key2", 1000, "val3")
        ).get();

        assertEquals(2, cache.calculations());

    }

}