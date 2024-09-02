package com.meerity.yourgym.constants.protein;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ProteinContent {
    LOW(BigDecimal.valueOf(5), "Low (5%)"),
    MEDIUM(BigDecimal.valueOf(15), "Medium (15%)"),
    HIGH(BigDecimal.valueOf(30), "High (30%)"),
    HIGHEST(BigDecimal.valueOf(50), "Highest (50%)");

    private final BigDecimal percentage;
    private final String description;

}
