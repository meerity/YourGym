package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.entity.products.Product;
import com.meerity.yourgym.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductPageController {

    private final ProductService productService;

    public ProductPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public String productPage(@PathVariable String productId, Model model) {
        Product product = productService.getProductById(Long.parseLong(productId));
        model.addAttribute("product", product);
        return "product-page";
    }
}
