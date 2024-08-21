package com.meerity.yourgym.service.product;

import com.meerity.yourgym.constants.ProductStatus;
import com.meerity.yourgym.jpa_specifications.builders.ProductSpecificationBuilder;
import com.meerity.yourgym.model.entity.products.Protein;
import com.meerity.yourgym.repositories.products.ProteinRepository;
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
public class ProteinService {

    private final ProductRepositoryProvider productRepositoryProvider;
    private final ProductPageableProvider productPageableProvider;


    public ProteinService(ProductRepositoryProvider productRepositoryProvider, ProductPageableProvider productPageableProvider) {
        this.productRepositoryProvider = productRepositoryProvider;
        this.productPageableProvider = productPageableProvider;
    }

    public Specification<Protein> getSpecifications(BigDecimal minPrice, BigDecimal maxPrice,
                                                    List<Long> brandIds, List<String> form, List<BigDecimal> proteinContent,
                                                    List<String> flavor, List<Integer> servingSize, List<BigDecimal> sugarContent, boolean isVegan){

        ProductSpecificationBuilder<Protein> specBuilder = new ProductSpecificationBuilder<Protein>()
                .withStatus(ProductStatus.ACTIVE)
                .withPriceRange(minPrice, maxPrice)
                .withBrands(brandIds)
                .withForm(form)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.PROTEIN, proteinContent)
                .withFlavor(flavor)
                .withServingSize(servingSize)
                .withContentPercent(ProductSpecificationBuilder.ProductContentTypeForPercents.SUGAR, sugarContent)
                .withVeganMark(isVegan);

        return specBuilder.build();
    }

    public Page<Protein> getPage(int pageNum, int size, String sortField,
                                 String sortDirection, Specification<Protein> spec) {

        ProteinRepository repo = productRepositoryProvider.getProteinRepository();
        Pageable pageable = productPageableProvider.getPageableWithSort(pageNum, size, sortField, sortDirection);

        return repo.findAll(spec, pageable);

    }
}
