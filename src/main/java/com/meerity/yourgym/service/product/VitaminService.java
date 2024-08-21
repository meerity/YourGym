package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.Vitamin;
import com.meerity.yourgym.repositories.products.VitaminRepository;
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
public class VitaminService {

    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;

    public VitaminService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<Vitamin> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                    List<Long> brandIds, List<Integer> servingSize, List<BigDecimal> vitaminContent) {

        ProductSpecificationBuilder<Vitamin> specBuilder = new ProductSpecificationBuilder<Vitamin>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withServingSize(servingSize)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.VITAMIN, vitaminContent);

        return specBuilder.build();
    }

    public Page<Vitamin> getPage(int pageNum, int size, String sortField,
                                 String sortDirection, Specification<Vitamin> spec){

        VitaminRepository repo = productRepositoryProvider.getVitaminRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);
    }
}
