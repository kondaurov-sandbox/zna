package znak;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ModelModule {

    @Value
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    class Recipe {
        @Length(min = 9, max = 9) @NotEmpty
        String seller;
        @Length(min = 9, max = 9) @NotEmpty
        String customer;
        @Valid @NotNull
        List<Product> products;
    }

    @Value
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    class Product {
        @NotEmpty
        String name;
        @Length(min = 13, max = 13) @NotEmpty
        String code;
    }

    @Value(staticConstructor = "of")
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    class SuccessResponse {
        Object success;
    }

    @Value(staticConstructor = "of")
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    class ErrorResponse {
        Object error;
    }
}
