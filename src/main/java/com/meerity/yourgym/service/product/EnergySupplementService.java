package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.EnergySupplement;
import com.meerity.yourgym.repositories.products.EnergySupplementRepository;
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
public class EnergySupplementService {


    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;

    public EnergySupplementService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<EnergySupplement> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                             List<Long> brandIds, List<Integer> caffeineContent,
                                                             List<String> flavor, List<Integer> servingSize, List<BigDecimal> sugarContent){

        ProductSpecificationBuilder<EnergySupplement> specBuilder = new ProductSpecificationBuilder<EnergySupplement>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withCaffeineContentMg(caffeineContent)
                .withFlavor(flavor)
                .withServingSize(servingSize)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.SUGAR, sugarContent);

        return specBuilder.build();
    }

    public Page<EnergySupplement> getPage(int pageNum, int size, String sortField,
                                          String sortDirection, Specification<EnergySupplement> spec){

        EnergySupplementRepository repo = productRepositoryProvider.getEnergySupplementRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);
    }
}
