package com.meerity.yourgym.constants.sport_drinks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SportDrinkServingSize {
    SINGLE_CAN_250ML("Single Can (250 ml)", 250),
    SINGLE_CAN_500ML("Single Can (500 ml)", 500),
    SINGLE_CAN_1L("Single Can (1 L)", 1000),
    MULTI_PACK_6_CANS("Multi-Pack (6 x 250 ml)", 1500),
    MULTI_PACK_12_CANS("Multi-Pack (12 x 500 ml)", 6000),
    POWDER_PACK_500G("Powder Pack (500 g)", 500),
    POWDER_PACK_1KG("Powder Pack (1 kg)", 1000),
    POWDER_PACK_2KG("Powder Pack (2 kg)", 2000),
    SINGLE_BOTTLE_500ML("Single Bottle (500 ml)", 500),
    SINGLE_BOTTLE_1L("Single Bottle (1 L)", 1000);

    private final String description;
    private final int grams;
}

