package com.meerity.yourgym.constants.protein;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ProteinSugarContent {
    VERY_HIGH(BigDecimal.valueOf(20), "Very High"),
    HIGH(BigDecimal.valueOf(15), "High"),
    MEDIUM(BigDecimal.valueOf(10), "Medium"),
    LOW(BigDecimal.valueOf(5), "Low"),
    VERY_LOW(BigDecimal.valueOf(1), "Very Low");

    private final BigDecimal percentage;
    private final String description;

}
