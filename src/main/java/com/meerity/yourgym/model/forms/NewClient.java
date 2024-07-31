package com.meerity.yourgym.model.forms;

import com.meerity.yourgym.annotations.UniqueField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewClient {

    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 75, message = "First name must be from 2 to 75 characters long")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 75, message = "Last name must be from 2 to 75 characters long")
    private String lastName;

    @UniqueField(type = UniqueField.FieldType.PHONE_NUMBER, message = "Phone number already registered")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String phoneNum;

    @UniqueField(type = UniqueField.FieldType.CARD_NUMBER, message = "This card number already registered")
    @NotBlank(message = "Card number must not be blank")
    @Pattern(regexp = "^\\d{6}$", message = "Please, provide a valid card number")
    private String cardNumber;

    private long trainerId;

}
