package com.meerity.yourgym.annotations;

import com.meerity.yourgym.validators.UniqueFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueFieldValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {
    String message() default "This field already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    FieldType type();
    enum FieldType {
        EMAIL,
        PHONE_NUMBER,
        CARD_NUMBER
    }
}
