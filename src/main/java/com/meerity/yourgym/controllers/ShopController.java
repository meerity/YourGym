package com.meerity.yourgym.controllers;

import com.meerity.yourgym.constants.energy_supplement.*;
import com.meerity.yourgym.constants.pre_workout_supplement.*;
import com.meerity.yourgym.constants.protein.*;
import com.meerity.yourgym.constants.sport_drinks.SportDrinkFlavors;
import com.meerity.yourgym.constants.sport_drinks.SportDrinkServingSize;
import com.meerity.yourgym.constants.sport_drinks.SportDrinkSugarContent;
import com.meerity.yourgym.constants.vitamin.VitaminContent;
import com.meerity.yourgym.constants.vitamin.VitaminServingSize;
import com.meerity.yourgym.constants.water.WaterServingSize;
import com.meerity.yourgym.model.entity.products.*;
import com.meerity.yourgym.service.CategoryService;
import com.meerity.yourgym.service.product.*;
import com.meerity.yourgym.service.provider.ProductTypeServiceProvider;
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

    private final CategoryService categoryService;
    private final ProductTypeServiceProvider productTypeServiceProvider;

    public ShopController(CategoryService categoryService, ProductTypeServiceProvider productTypeServiceProvider) {
        this.categoryService = categoryService;
        this.productTypeServiceProvider = productTypeServiceProvider;
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

        final String CATEGORY_NAME = "PROTEINS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        ProteinService proteinService = productTypeServiceProvider.getProteinService();
        Specification<Protein> spec = proteinService.getSpecifications(minPrice, maxPrice, brandIds, form, proteinContent,
                flavor, servingSize, sugarContent, isVegan);
        Page<Protein> productPage = proteinService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("forms", ProteinForms.values());
        model.addAttribute("proteinContents", ProteinContent.values());
        model.addAttribute("flavors", ProteinFlavors.values());
        model.addAttribute("servingSizes", ProteinServingSize.values());
        model.addAttribute("sugarContent", ProteinSugarContent.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/proteins";
    }

    @GetMapping("/energy-supplements")
    public String displayEnergySupplementShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "32") int size,
                                                  @RequestParam(defaultValue = "priceAsc") String sort,
                                                  @RequestParam(required = false) BigDecimal minPrice,
                                                  @RequestParam(required = false) BigDecimal maxPrice,
                                                  @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                                  @RequestParam(required = false) List<Integer> caffeineContent,
                                                  @RequestParam(required = false) List<String> flavor,
                                                  @RequestParam(required = false) List<Integer> servingSize,
                                                  @RequestParam(required = false) List<BigDecimal> sugarContent,
                                                  Model model) {

        final String CATEGORY_NAME = "ENERGY_SUPPLEMENTS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        EnergySupplementService energySupplementService = productTypeServiceProvider.getEnergySupplementService();
        Specification<EnergySupplement> spec = energySupplementService.getSpecifications(minPrice, maxPrice, brandIds, caffeineContent,
                flavor, servingSize, sugarContent);
        Page<EnergySupplement> productPage = energySupplementService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("caffeineContents", EnergySupplementCaffeineContent.values());
        model.addAttribute("flavors", EnergySupplementFlavors.values());
        model.addAttribute("servingSizes", EnergySupplementServingSize.values());
        model.addAttribute("sugarContent", EnergySupplementSugarContent.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/energy-supplements";
    }

    @GetMapping("/pre-workout")
    public String displayPreWorkoutSupplementShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                                      @RequestParam(defaultValue = "32") int size,
                                                      @RequestParam(defaultValue = "priceAsc") String sort,
                                                      @RequestParam(required = false) BigDecimal minPrice,
                                                      @RequestParam(required = false) BigDecimal maxPrice,
                                                      @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                                      @RequestParam(required = false) List<Integer> caffeineContent,
                                                      @RequestParam(required = false) List<String> flavor,
                                                      @RequestParam(required = false) List<Integer> servingSize,
                                                      @RequestParam(required = false) List<BigDecimal> sugarContent,
                                                      Model model) {

        final String CATEGORY_NAME = "PRE_WORKOUT_SUPPLEMENTS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        PreWorkoutSupplementService preWorkoutSupplementService = productTypeServiceProvider.getPreWorkoutSupplementService();
        Specification<PreWorkoutSupplement> spec = preWorkoutSupplementService.getSpecifications(minPrice, maxPrice, brandIds, caffeineContent,
                flavor, servingSize, sugarContent);
        Page<PreWorkoutSupplement> productPage = preWorkoutSupplementService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("caffeineContents", PreWorkoutCaffeineContent.values());
        model.addAttribute("flavors", PreWorkoutFlavors.values());
        model.addAttribute("servingSizes", PreWorkoutServingSize.values());
        model.addAttribute("sugarContent", PreWorkoutSugarContent.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/pre-workout";
    }

    @GetMapping("/vitamins")
    public String displayVitaminsShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "32") int size,
                                          @RequestParam(defaultValue = "priceAsc") String sort,
                                          @RequestParam(required = false) BigDecimal minPrice,
                                          @RequestParam(required = false) BigDecimal maxPrice,
                                          @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                          @RequestParam(required = false) List<Integer> servingSize,
                                          @RequestParam(required = false) List<BigDecimal> vitaminContent,
                                          Model model) {

        final String CATEGORY_NAME = "VITAMINS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        VitaminService vitaminService = productTypeServiceProvider.getVitaminService();
        Specification<Vitamin> spec = vitaminService.getSpecifications(minPrice, maxPrice, brandIds, servingSize,
                vitaminContent);
        Page<Vitamin> productPage = vitaminService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("servingSizes", VitaminServingSize.values());
        model.addAttribute("vitaminContent", VitaminContent.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/vitamins";
    }

    @GetMapping("/sport-drinks")
    public String displaySportDrinksShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "32") int size,
                                             @RequestParam(defaultValue = "priceAsc") String sort,
                                             @RequestParam(required = false) BigDecimal minPrice,
                                             @RequestParam(required = false) BigDecimal maxPrice,
                                             @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                             @RequestParam(required = false) List<String> flavor,
                                             @RequestParam(required = false) List<Integer> servingSize,
                                             @RequestParam(required = false) List<BigDecimal> sugarContent,
                                             Model model) {

        final String CATEGORY_NAME = "SPORT_DRINKS";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        SportDrinkService sportDrinkService = productTypeServiceProvider.getSportDrinkService();
        Specification<SportDrink> spec = sportDrinkService.getSpecifications(minPrice, maxPrice, brandIds, flavor, servingSize,
                sugarContent);
        Page<SportDrink> productPage = sportDrinkService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("flavors", SportDrinkFlavors.values());
        model.addAttribute("servingSizes", SportDrinkServingSize.values());
        model.addAttribute("sugarContent", SportDrinkSugarContent.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/sport-drinks";
    }

    @GetMapping("/water")
    public String displayWaterShopPage(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "32") int size,
                                       @RequestParam(defaultValue = "priceAsc") String sort,
                                       @RequestParam(required = false) BigDecimal minPrice,
                                       @RequestParam(required = false) BigDecimal maxPrice,
                                       @RequestParam(name ="brands", required = false) List<Long> brandIds,
                                       @RequestParam(required = false) List<Integer> servingSize,
                                       Model model) {

        final String CATEGORY_NAME = "WATER";

        String[] sortDirAndSortFiled = getSortDirAndSortField(sort);
        String sortDir = sortDirAndSortFiled[0];
        String sortField = sortDirAndSortFiled[1];

        WaterService waterService = productTypeServiceProvider.getWaterService();
        Specification<Water> spec = waterService.getSpecifications(minPrice, maxPrice, brandIds, servingSize);
        Page<Water> productPage = waterService.getPage(pageNum, size, sortField, sortDir, spec);

        model.addAttribute("servingSizes", WaterServingSize.values());

        populateModel(model, productPage, pageNum, size, sort, CATEGORY_NAME);
        return "categories-shop-pages/water";
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

    private void populateModel(Model model, Page<?> productPage, int pageNum, int size, String sort, String categoryName) {

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalProducts", productPage.getTotalElements());

        int totalPages = productPage.getTotalPages() == 0 ? 1 : productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        model.addAttribute("manufacturers", categoryService.getManufacturersByCategoryName(categoryName));
    }
}
