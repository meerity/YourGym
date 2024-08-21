package com.meerity.yourgym.constants.vitamin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VitaminServingSize {

    SINGLE_TABLET("Single Tablet", 1),
    BOTTLE_30_TABLETS("Bottle (30 Tablets)", 15),
    BOTTLE_60_TABLETS("Bottle (60 Tablets)", 30),
    BOTTLE_120_TABLETS("Bottle (120 Tablets)", 60),

    POWDER_PACK_200G("Powder Pack (200 g)", 200),
    POWDER_PACK_500G("Powder Pack (500 g)", 500),
    POWDER_PACK_1KG("Powder Pack (1 kg)", 1000),

    LIQUID_100ML("Liquid (100 ml)", 100),
    LIQUID_200ML("Liquid (200 ml)", 200),
    LIQUID_500ML("Liquid (500 ml)", 500);

    private final String description;
    private final int grams;

}
