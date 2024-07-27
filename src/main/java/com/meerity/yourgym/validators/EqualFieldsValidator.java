package com.meerity.yourgym.validators;


import com.meerity.yourgym.annotations.EqualFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

    private String field1;
    private String field2;

    @Override
    public void initialize(EqualFields constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object field1Value = new BeanWrapperImpl(o).getPropertyValue(field1);
        Object field2Value = new BeanWrapperImpl(o).getPropertyValue(field2);
        if(field1Value != null && field2Value != null) {
            return field1Value.equals(field2Value);
        }
        return false;
    }
}
