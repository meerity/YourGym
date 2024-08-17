package com.meerity.yourgym.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "client_card")
public class ClientCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private long cardId;

    @Setter
    @Column(name = "card_number", length = 6, unique = true, nullable = false)
    private String cardNumber;

    @Column(name = "last_payment_date", nullable = false)
    private LocalDate lastPaymentDate;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Order> orders;
}
