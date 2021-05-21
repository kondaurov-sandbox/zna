package znak;

import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController(value = "/")
class MainCtrl {

    @GetMapping
    public String index() {
        return "Hey";
    }

    @PostMapping("/recipe")
    public Object index2(@Validated @RequestBody ModelModule.Recipe recipe) {
        return ModelModule.SuccessResponse.of("Correct recipe :)");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationExceptions(
        MethodArgumentNotValidException ex
    ) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ModelModule.ErrorResponse.of(errors);
    }

}
