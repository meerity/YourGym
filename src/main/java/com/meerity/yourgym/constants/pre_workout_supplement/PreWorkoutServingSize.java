package com.meerity.yourgym.constants.pre_workout_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PreWorkoutServingSize {

    SINGLE_SCOOP_10G("Single Scoop (10 g)", 10),
    DOUBLE_SCOOP_20G("Double Scoop (20 g)", 20),
    SMALL_PACK_150G("Small Pack (150 g)", 150),
    MEDIUM_PACK_300G("Medium Pack (300 g)", 300),
    LARGE_PACK_500G("Large Pack (500 g)", 500),
    BULK_PACK_1KG("Bulk Pack (1 kg)", 1000);

    private final String description;
    private final int grams;
}
