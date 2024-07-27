package com.meerity.yourgym.model;

import com.meerity.yourgym.annotations.EqualFields;
import com.meerity.yourgym.annotations.Password;
import com.meerity.yourgym.annotations.UniqueField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@EqualFields.FieldsToValidate(
        @EqualFields(
                field1 = "password",
                field2 = "confirmPassword",
                message = "Passwords are not equal"
        )

)
public class RegistrationForm {

    @UniqueField(type = UniqueField.FieldType.EMAIL, message = "Account with this email already registered")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Password(message = "Password should be from 8 to 32 characters long and contain" +
            "  at least one lowercase letter, uppercase letter, digit and special character")
    private String password;

    @NotBlank(message = "Password must not be blank")
    private String confirmPassword;

    @NotBlank(message = "Card number must not be blank")
    private String cardNumber;
}
