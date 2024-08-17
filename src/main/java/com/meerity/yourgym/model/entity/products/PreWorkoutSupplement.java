package com.meerity.yourgym.model.entity.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pre_workout_supplements")
public class PreWorkoutSupplement extends Product {

    @Column(precision = 5, scale = 2, name = "caffeine_content_percent", nullable = false)
    private BigDecimal caffeineContent;

    @Column(length = 50, nullable = false)
    private String flavor;

    @Column(name = "serving_size_g", nullable = false)
    private int servingSize;

    @Column(precision = 5, scale = 2, name = "sugar_content_percent", nullable = false)
    private BigDecimal sugarContent;

}

