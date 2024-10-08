package com.meerity.yourgym.model.entity;

import com.meerity.yourgym.constants.ContactStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private long contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "contact_name", length = 100)
    private String contactName;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    @Column(name = "contact_mobile_number", length = 10)
    private String contactMobileNum;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    @Column(name = "contact_email", length = 50)
    private String contactEmail;

    @NotBlank(message = "Message must not be blank")
    @Column(name = "contact_message", length = 500)
    private String message;

    @Column(name = "contact_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactStatus status;

}

