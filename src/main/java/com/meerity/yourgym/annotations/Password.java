package com.meerity.yourgym.annotations;

import com.meerity.yourgym.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PasswordValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Please enter a valid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
