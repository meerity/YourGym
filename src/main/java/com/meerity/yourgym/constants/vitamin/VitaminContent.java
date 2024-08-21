package com.meerity.yourgym.constants.vitamin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum VitaminContent {
    LOW(BigDecimal.valueOf(5), "Low (5%)"),
    MEDIUM(BigDecimal.valueOf(15), "Medium (15%)"),
    HIGH(BigDecimal.valueOf(30), "High (30%)"),
    VERY_HIGH(BigDecimal.valueOf(50), "Very High (50%)");

    private final BigDecimal percentage;
    private final String description;

}
