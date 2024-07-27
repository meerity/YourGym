package com.meerity.yourgym.validators;

import com.meerity.yourgym.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static String PASSWORD_REGEX;


    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.matches(PASSWORD_REGEX);
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        //At least: one uppercase letter, one lowercase letter, one digit, one of symbols: !?/\-_()
        //Length from 8 to 32
        PASSWORD_REGEX = "^(?=.*\\p{Ll})(?=.*\\p{Lu})(?=.*\\d)(?=.*[!?\\-/\\\\_()])[\\p{Ll}\\p{Lu}\\d!?\\-/\\\\_()]{8,32}$";

    }
}
