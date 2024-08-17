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
@Table(name = "vitamins_minerals")
public class Vitamin extends Product {

    @Column(name = "serving_size_g", nullable = false)
    private int servingSize;

    @Column(precision = 5, scale = 2, name = "vitamin_content_percent", nullable = false)
    private BigDecimal vitaminContent;

    @Column(precision = 5, scale = 2, name = "mineral_content_percent", nullable = false)
    private BigDecimal mineralContent;
}
