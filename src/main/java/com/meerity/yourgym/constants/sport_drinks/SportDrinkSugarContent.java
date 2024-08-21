package com.meerity.yourgym.constants.sport_drinks;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum SportDrinkSugarContent {
    VERY_HIGH(BigDecimal.valueOf(20), "Very High (20%)"),
    HIGH(BigDecimal.valueOf(15), "High (15%)"),
    MEDIUM(BigDecimal.valueOf(10), "Medium (10%)"),
    LOW(BigDecimal.valueOf(5), "Low (5%)"),
    VERY_LOW(BigDecimal.valueOf(1), "Very Low (1%)");

    private final BigDecimal percentage;
    private final String description;

}
