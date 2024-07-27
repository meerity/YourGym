package com.meerity.yourgym.model;

import jakarta.persistence.*;
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

    @Column(name = "trainer_first_name")
    private String trainerFirstName;

    @Column(name = "trainer_last_name")
    private String trainerLastName;

    @OneToMany(mappedBy = "trainer")
    private List<ClientCard> clientCards;

}
