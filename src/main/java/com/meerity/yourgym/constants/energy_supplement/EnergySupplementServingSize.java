package com.meerity.yourgym.constants.energy_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnergySupplementServingSize {

    LARGE_CAN_500ML("Large Can (500 ml)", 500),
    MEDIUM_CAN_330ML("Medium Can (330 ml)", 330),
    SMALL_CAN_250ML("Small Can (250 ml)", 250),

    MULTI_PACK_6_CANS("Multi-Pack (6 x 250 ml)", 1500),
    MULTI_PACK_12_CANS("Multi-Pack (12 x 330 ml)", 3960),

    SINGLE_SHOT_60ML("Single Shot (60 ml)", 60),
    SINGLE_SHOT_100ML("Single Shot (100 ml)", 100),

    POWDER_PACK_500G("Powder Pack (500 g)", 500),
    POWDER_PACK_1KG("Powder Pack (1 kg)", 1000);

    private final String description;
    private final int grams;

}
