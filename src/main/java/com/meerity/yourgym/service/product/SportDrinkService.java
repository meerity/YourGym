package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.SportDrink;
import com.meerity.yourgym.repositories.products.SportDrinkRepository;
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
public class SportDrinkService {

    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;

    public SportDrinkService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<SportDrink> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                      List<Long> brandIds, List<String> flavor, List<Integer> servingSize, List<BigDecimal> sugarContent) {

        ProductSpecificationBuilder<SportDrink> builder = new ProductSpecificationBuilder<SportDrink>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withFlavor(flavor)
                .withServingSize(servingSize)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.SUGAR, sugarContent);
        return builder.build();
    }

    public Page<SportDrink> getPage(int pageNum, int size, String sortField,
                                    String sortDirection, Specification<SportDrink> spec) {

        SportDrinkRepository repo = productRepositoryProvider.getSportDrinkRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);
    }
}
