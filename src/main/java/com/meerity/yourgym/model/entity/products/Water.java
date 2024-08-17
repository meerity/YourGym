package com.meerity.yourgym.model.entity.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "water")
public class Water extends Product {

    @Column(length = 50, nullable = false)
    private String flavor;

    @Column(name = "serving_size_g", nullable = false)
    private int servingSize;

}
