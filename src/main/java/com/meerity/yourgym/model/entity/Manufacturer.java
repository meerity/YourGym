package com.meerity.yourgym.model.entity;

import com.meerity.yourgym.model.entity.products.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "manufacturers")
public class Manufacturer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long manufacturerId;

    @Column(length = 50, name = "manufacturer_name", nullable = false)
    private String manufacturerName;

    @OneToMany
    Set<Product> products;

    @ManyToMany(mappedBy = "manufacturers")
    Set<Category> categories;
}

