package com.meerity.yourgym.model.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditForm {

    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 75, message = "First name must be from 2 to 75 characters long")
    private String formFirstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 75, message = "Last name must be from 2 to 75 characters long")
    private String formLastName;


    @Pattern(regexp = "^\\d{10}(\\d{2})?$", message = "Invalid mobile number")
    private String formPhoneNum;


    @Email(message = "Please provide a valid email address")
    private String formEmail;
}
