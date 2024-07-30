package com.meerity.yourgym.model;

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
@Table(name = "person")
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 75, message = "First name must be from 2 to 75 characters long")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 75, message = "Last name must be from 2 to 75 characters long")
    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^\\d{10}(\\d{2})?$", message = "Invalid mobile number")
    @Column(name = "person_phone")
    private String phoneNum;

    @Email(message = "Please provide a valid email address")
    @Column(name = "person_email")
    private String email;

    @Column(name = "person_psw")
    private String psw;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ClientCard.class)
    @JoinColumn(name = "client_card_id", referencedColumnName = "card_id")
    private ClientCard card;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

}
