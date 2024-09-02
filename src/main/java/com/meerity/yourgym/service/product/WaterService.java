package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.Water;
import com.meerity.yourgym.repositories.products.WaterRepository;
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
public class WaterService {

    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;

    public WaterService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<Water> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                  List<Long> brandIds, List<Integer> servingSize) {

        ProductSpecificationBuilder<Water> specBuilder = new ProductSpecificationBuilder<Water>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withServingSize(servingSize);
        return specBuilder.build();
    }

    public Page<Water> getPage(int pageNum, int size, String sortField,
                                 String sortDirection, Specification<Water> spec){

        WaterRepository repo = productRepositoryProvider.getWaterRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);
    }
}
