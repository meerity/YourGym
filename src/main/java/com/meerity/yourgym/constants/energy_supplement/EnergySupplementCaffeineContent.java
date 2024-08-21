package com.meerity.yourgym.constants.energy_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum EnergySupplementCaffeineContent {
    LOW(BigDecimal.valueOf(50), "Low (50 mg)"),
    MEDIUM(BigDecimal.valueOf(100), "Medium (100 mg)"),
    HIGH(BigDecimal.valueOf(200), "High (200 mg)"),
    VERY_HIGH(BigDecimal.valueOf(300), "Very High (300 mg)");

    private final BigDecimal milligrams;
    private final String description;

}
