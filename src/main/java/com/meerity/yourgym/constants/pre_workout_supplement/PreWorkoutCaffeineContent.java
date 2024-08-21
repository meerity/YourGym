package com.meerity.yourgym.constants.pre_workout_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum PreWorkoutCaffeineContent {
    LOW(BigDecimal.valueOf(100), "Low (100 mg)"),
    MEDIUM(BigDecimal.valueOf(200), "Medium (200 mg)"),
    HIGH(BigDecimal.valueOf(300), "High (300 mg)"),
    VERY_HIGH(BigDecimal.valueOf(400), "Very High (400 mg)");

    private final BigDecimal milligrams;
    private final String description;
}

