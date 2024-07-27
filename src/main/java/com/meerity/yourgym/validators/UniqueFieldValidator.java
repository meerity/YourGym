package com.meerity.yourgym.validators;

import com.meerity.yourgym.annotations.UniqueField;
import com.meerity.yourgym.repositories.ClientCardRepository;
import com.meerity.yourgym.repositories.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClientCardRepository clientCardRepository;

    private UniqueField.FieldType fieldType;


    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fieldType = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (fieldType == null){
            return true;
        }
        return switch (fieldType) {
            case EMAIL -> personRepository.findByEmail(s) == null;
            case PHONE_NUMBER -> personRepository.findByPhoneNum(s) == null;
            case CARD_NUMBER -> clientCardRepository.findByCardNumber(s) == null;
        };
    }
}
