package com.meerity.yourgym.model.entity.products;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.model.entity.BaseEntity;
import com.meerity.yourgym.model.entity.Category;
import com.meerity.yourgym.model.entity.Manufacturer;
import com.meerity.yourgym.model.entity.OrderItem;
import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    private Category category;

    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "manufacturerId")
    private Manufacturer manufacturer;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @Column(length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(length = 128, name = "small_image_url")
    private String smallImageUrl;

    @Column(length = 128, name = "large_image_url")
    private String largeImageUrl;

    @OneToMany(mappedBy = "product")
    Set<OrderItem> items;
}
