package com.meerity.yourgym.service.product;

import com.meerity.yourgym.model.entity.products.Product;
import com.meerity.yourgym.repositories.products.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(long id) {
        return productRepository.findByProductId(id);
    }
}
