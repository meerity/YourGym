package com.meerity.yourgym.controllers;

import com.meerity.yourgym.constants.protein.*;
import com.meerity.yourgym.model.entity.products.Protein;
import com.meerity.yourgym.service.CategoryService;
import com.meerity.yourgym.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ShopController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String displayCategoriesPage(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "categories";
    }

    @GetMapping("/proteins")
    public String displayProteinShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(defaultValue = "32") int size,
                                  @RequestParam(defaultValue = "priceAsc") String sort,
                                  @RequestParam(required = false) BigDecimal minPrice,
                                  @RequestParam(required = false) BigDecimal maxPrice,
                                  @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                  @RequestParam(required = false) List<String> form,
                                  @RequestParam(required = false) List<BigDecimal> proteinContent,
                                  @RequestParam(required = false) List<String> flavor,
                                  @RequestParam(required = false) List<Integer> servingSize,
                                  @RequestParam(required = false) List<BigDecimal> sugarContent,
                                  @RequestParam(required = false) boolean isVegan,
                                  Model model) {

        String categoryName = "PROTEINS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (maxPrice == null) {
            maxPrice = BigDecimal.valueOf(100_000);
        }
        Specification<Protein> spec = productService.getProteinSpecifications(minPrice, maxPrice, brandIds, form, proteinContent,
                flavor, servingSize, sugarContent, isVegan);
        Page<Protein> productPage = productService.getActiveProteinsWithPaginationAndFilters(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("forms", ProteinForms.values());
        model.addAttribute("proteinContents", ProteinContent.values());
        model.addAttribute("flavors", ProteinFlavors.values());
        model.addAttribute("servingSizes", ProteinServingSize.values());
        model.addAttribute("sugarContent", ProteinSugarContent.values());

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalProducts", productPage.getTotalElements());

        int totalPages = productPage.getTotalPages() == 0 ? 1 : productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        model.addAttribute("manufacturers", categoryService.getManufacturersByCategoryName(categoryName));
        return "proteins";
    }


    private String[] getSortDirAndSortField(String sort) {
        String sortDir;
        String sortField;
        if (sort.endsWith("Asc")) {
            sortDir = "asc";
            sortField = sort.substring(0, sort.length() - 3);
        } else if (sort.endsWith("Desc")) {
            sortDir = "desc";
            sortField = sort.substring(0, sort.length() - 4);
        } else {
            log.error("Invalid sort param: {}", sort);
            throw new IllegalArgumentException("Invalid sort direction");
        }
        return new String[] {sortDir, sortField};
    }
}
