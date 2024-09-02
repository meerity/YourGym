package com.meerity.yourgym.jpa_specifications.builders;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.model.entity.products.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static com.meerity.yourgym.jpa_specifications.ProductSpecifications.*;

public class ProductSpecificationBuilder<T extends Product> {

    private Specification<T> specification;

    public ProductSpecificationBuilder() {
        this.specification = Specification.where(null);
    }

    public ProductSpecificationBuilder<T> withStatus(ProductStatus status) {
        if (status != null) {
            this.specification = specification.and(hasStatus(status));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (maxPrice == null) {
            maxPrice = BigDecimal.valueOf(100_000);
        }
        this.specification = specification.and(hasPriceBetween(minPrice, maxPrice));
        return this;
    }

    public ProductSpecificationBuilder<T> withBrands(List<Long> brandIds) {
        if (brandIds != null && !brandIds.isEmpty()) {
            this.specification = specification.and(hasBrands(brandIds));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withServingSize(List<Integer> servingSize) {
        if (servingSize != null) {
            this.specification = specification.and(hasServingSize(servingSize));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withCaffeineContentMg(List<Integer> caffeineMg) {
        if (caffeineMg != null) {
            this.specification = specification.and(hasCaffeineContent(caffeineMg));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withContentPercent(ProductContentTypeForPercents type, List<BigDecimal> percent) {
        if (percent != null) {
            switch (type) {
                case SUGAR ->  this.specification = specification.and(hasSugarContent(percent));
                case PROTEIN -> this.specification = specification.and(hasProteinContents(percent));
                case VITAMIN -> this.specification = specification.and(hasVitaminContent(percent));
            }
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withForm(List<String> forms) {
        if (forms != null && !forms.isEmpty()) {
            this.specification = specification.and(hasForms(forms));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withFlavor(List<String> flavor) {
        if (flavor != null) {
            this.specification = specification.and(hasFlavors(flavor));
        }
        return this;
    }

    public ProductSpecificationBuilder<T> withVeganMark(boolean vegan) {
        this.specification = specification.and(isVegan(vegan));
        return this;
    }

    public Specification<T> build() {
        return specification;
    }

    public enum ProductContentTypeForPercents {
        SUGAR,
        PROTEIN,
        VITAMIN,
    }
}
