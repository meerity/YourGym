package com.meerity.yourgym.constants.water;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WaterServingSize {
    SINGLE_BOTTLE_250ML("Single Bottle (250 ml)", 250),
    SINGLE_BOTTLE_500ML("Single Bottle (500 ml)", 500),
    SINGLE_BOTTLE_1L("Single Bottle (1 L)", 1000),
    SINGLE_BOTTLE_2L("Single Bottle (2 L)", 2000),
    MULTI_PACK_6_BOTTLES("Multi-Pack (6 x 500 ml)", 3000),
    MULTI_PACK_12_BOTTLES("Multi-Pack (12 x 500 ml)", 6000);

    private final String description;
    private final int grams;
}
