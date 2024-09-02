package com.meerity.yourgym.jpa_specifications;


import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.model.entity.Manufacturer;
import com.meerity.yourgym.model.entity.products.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class ProductSpecifications {

    public static <T extends Product> Specification<T> hasStatus(ProductStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static <T extends Product> Specification<T> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, cb) -> cb.between(root.get("price"), minPrice, maxPrice);
    }

    public static <T extends Product> Specification<T> hasBrands(List<Long> brandIds) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Join<T, Manufacturer> join = root.join("manufacturer");
            return join.get("manufacturerId").in(brandIds);
        };
    }

    public static <T extends Product> Specification<T> hasServingSize(List<Integer> g) {
        return (root, query, cb) -> root.get("servingSize").in(g);
    }

    public static <T extends Product> Specification<T> hasSugarContent(List<BigDecimal> percents) {
        return (root, query, cb) -> root.get("sugarContent").in(percents);
    }

    public static <T extends Product> Specification<T> hasCaffeineContent(List<Integer> mg) {
        return (root, query, cb) -> root.get("caffeineContent").in(mg);
    }

    public static <T extends Product> Specification<T> hasVitaminContent(List<BigDecimal> percents) {
        return (root, query, cb) -> root.get("vitaminContent").in(percents);
    }

    public static <T extends Product> Specification<T> hasProteinContents(List<BigDecimal> percents) {
        return (root, query, cb) -> root.get("proteinContent").in(percents);
    }

    public static<T extends Product> Specification<T> hasFlavors(List<String> flavors) {
        return (root, query, cb) -> root.get("flavor").in(flavors);
    }

    public static <T extends Product> Specification<T> hasForms(List<String> forms) {
        return (root, query, cb) -> root.get("form").in(forms);
    }

    public static <T extends Product> Specification<T> isVegan(boolean vegan) {
        return (root, query, cb) -> cb.equal(root.get("isVegan"), vegan);
    }

}
