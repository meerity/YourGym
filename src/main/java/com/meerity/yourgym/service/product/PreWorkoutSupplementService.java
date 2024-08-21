package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.PreWorkoutSupplement;
import com.meerity.yourgym.repositories.products.PreWorkoutSupplementRepository;
import com.meerity.yourgym.repositories.products.provider.ProductRepositoryProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class PreWorkoutSupplementService {


    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;

    public PreWorkoutSupplementService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<PreWorkoutSupplement> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                                List<Long> brandIds, List<Integer> caffeineContent,
                                                                List<String> flavor, List<Integer> servingSize, List<BigDecimal> sugarContent) {

        ProductSpecificationBuilder<PreWorkoutSupplement> specBuilder = new ProductSpecificationBuilder<PreWorkoutSupplement>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withCaffeineContentMg(caffeineContent)
                .withFlavor(flavor)
                .withServingSize(servingSize)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.SUGAR, sugarContent);

        return specBuilder.build();
    }

    public Page<PreWorkoutSupplement> getPage(int pageNum, int size, String sortField,
                                              String sortDirection, Specification<PreWorkoutSupplement> spec) {

        PreWorkoutSupplementRepository repo = productRepositoryProvider.getPreWorkoutSupplementRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);
    }
}
