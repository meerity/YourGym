package com.meerity.yourgym.model.entity;

import com.meerity.yourgym.model.entity.products.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_internal_name", length = 35, nullable = false)
    private String internalName;

    @Column(name = "category_prod_name", length = 75, nullable = false)
    private String prodName;

    @Column(name = "category_image_url")
    private String imageUrl;

    @Column(name = "category_page_url", length = 128)
    private String pageUrl;

    @OneToMany
    Set<Product> products;

    @ManyToMany
            @JoinTable(
                    name = "category_manufacturer",
                    joinColumns = @JoinColumn(name = "category_id"),
                    inverseJoinColumns = @JoinColumn(name = "manufacturer_id")
            )
    Set<Manufacturer> manufacturers;
}
