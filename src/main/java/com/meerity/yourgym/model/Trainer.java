package com.meerity.yourgym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "trainer")
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private long trainerId;

    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 75, message = "First name must be from 2 to 75 characters long")
    @Column(name = "trainer_first_name")
    private String trainerFirstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 75, message = "Last name must be from 2 to 75 characters long")
    @Column(name = "trainer_last_name")
    private String trainerLastName;

    @OneToMany(mappedBy = "trainer")
    private List<ClientCard> clientCards;

}
