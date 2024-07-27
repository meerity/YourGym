package com.meerity.yourgym.model;

import jakarta.persistence.*;
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


    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "person_phone")
    private String phoneNum;


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
