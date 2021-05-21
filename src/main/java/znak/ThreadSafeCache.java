package znak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.function.Function;

public class ThreadSafeCache<K, V> {

    static Logger logger = LoggerFactory.getLogger(ThreadSafeCache.class);

    private final ConcurrentHashMap<K, CompletableFuture<V>> cache = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private Integer _calculations = 0;

    Integer calculations() { return _calculations; }

    CompletableFuture<V> compute(K k, Function<K, V> f) {
        if (this.cache.containsKey(k)) {
            return this.cache.get(k);
        }

        var res = CompletableFuture.supplyAsync(() -> {
            logger.info(String.format("Calculating value for key: %s", k.toString()));
            _calculations += 1;
            return f.apply(k);
        }, executor);

        this.cache.put(k, res);
        return res;
    }

}