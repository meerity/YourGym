package com.meerity.yourgym.service.provider;

import com.meerity.yourgym.service.product.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProductTypeServiceProvider {

    private final ProteinService proteinService;
    private final PreWorkoutSupplementService preWorkoutSupplementService;
    private final EnergySupplementService energySupplementService;
    private final SportDrinkService sportDrinkService;
    private final VitaminService vitaminService;
    private final WaterService waterService;

    public ProductTypeServiceProvider(ProteinService proteinService, PreWorkoutSupplementService preWorkoutSupplementService, EnergySupplementService energySupplementService, SportDrinkService sportDrinkService, VitaminService vitaminService, WaterService waterService) {
        this.proteinService = proteinService;
        this.preWorkoutSupplementService = preWorkoutSupplementService;
        this.energySupplementService = energySupplementService;
        this.sportDrinkService = sportDrinkService;
        this.vitaminService = vitaminService;
        this.waterService = waterService;
    }

}
