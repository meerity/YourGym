package com.meerity.yourgym.constants.protein;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProteinServingSize {

    LARGE_BAG_10KG("King Size Bag (10 kg)", 10000),
    LARGE_BAG_5KG("Large Bag (5 kg)", 5000),

    MEDIUM_BAG_2500G("Medium Large Bag (2.5 kg)", 2500),
    MEDIUM_BAG_1KG("Medium Bag (1 kg)", 1000),
    MEDIUM_BAG_500G("Small Bag (500 g)", 500),


    MULTI_BAR_PACK("Multi-Bar Pack (e.g., 5 x 50 g)", 250),
    SINGLE_BAR_70G("Single Bar (70 g)", 70),
    SINGLE_BAR_50G("Single Bar (50 g)", 50),

    TABLET_PACK_60("Multi-Tablet Pack (e.g., 60 tablets)", 60),
    TABLET_PACK_30("Multi-Tablet Pack (e.g., 30 tablets)", 30);

    private final String description;
    private final int grams;

}
