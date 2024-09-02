package com.meerity.yourgym.repositories.products.provider;

import com.meerity.yourgym.repositories.products.*;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProductRepositoryProvider {
    private final ProductRepository productRepository;
    private final ProteinRepository proteinRepository;
    private final PreWorkoutSupplementRepository preWorkoutSupplementRepository;
    private final EnergySupplementRepository energySupplementRepository;
    private final VitaminRepository vitaminRepository;
    private final SportDrinkRepository sportDrinkRepository;
    private final WaterRepository waterRepository;


    public ProductRepositoryProvider(ProductRepository productRepository, ProteinRepository proteinRepository, PreWorkoutSupplementRepository preWorkoutSupplementRepository, EnergySupplementRepository energySupplementRepository, VitaminRepository vitaminRepository, SportDrinkRepository sportDrinkRepository, WaterRepository waterRepository) {
        this.productRepository = productRepository;
        this.proteinRepository = proteinRepository;
        this.preWorkoutSupplementRepository = preWorkoutSupplementRepository;
        this.energySupplementRepository = energySupplementRepository;
        this.vitaminRepository = vitaminRepository;
        this.sportDrinkRepository = sportDrinkRepository;
        this.waterRepository = waterRepository;
    }
}
