package com.meerity.yourgym.service;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpaSpecifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.Protein;
import com.meerity.yourgym.repositories.products.*;
import com.meerity.yourgym.repositories.products.factory.ProductRepositoryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class ProductService {

    private final ProductRepositoryFactory productRepositoryFactory;

    public ProductService(ProductRepositoryFactory productRepositoryFactory) {
        this.productRepositoryFactory = productRepositoryFactory;
    }

    private Pageable getPageableWithSort(String sortField, String sortDirection, int pageNum, int size) {
        Sort sort = Sort.by(
                sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        return PageRequest.of(pageNum - 1, size, sort);
    }

    public Specification<Protein> getProteinSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                           List<Long> brandIds, List<String> form, List<BigDecimal> proteinContent,
                                                           List<String> flavor, List<Integer> servingSize, List<BigDecimal> sugarContent, boolean isVegan){

        ProductSpecificationBuilder<Protein> specBuilder = new ProductSpecificationBuilder<Protein>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withForm(form)
                .hasContentPercent(ProductSpecificationBuilder.ProductContentType.PROTEIN, proteinContent)
                .withFlavor(flavor)
                .withServingSize(servingSize)
                .hasContentPercent(ProductSpecificationBuilder.ProductContentType.SUGAR, sugarContent)
                .withVeganMark(isVegan);

        return specBuilder.build();
    }

    public Page<Protein> getActiveProteinsWithPaginationAndFilters(int pageNum, int size, String sortField,
                                                                             String sortDirection, Specification<Protein> spec) {

        ProteinRepository proteinRepository = productRepositoryFactory.getProteinRepository();
        Pageable pageable = getPageableWithSort(sortField, sortDirection, pageNum, size);

        return proteinRepository.findAll(spec, pageable);

    }

}
