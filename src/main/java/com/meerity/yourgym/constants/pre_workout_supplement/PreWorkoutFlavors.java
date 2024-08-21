package com.meerity.yourgym.constants.pre_workout_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PreWorkoutFlavors {
    FRUIT_PUNCH("Fruit Punch"),
    WATERMELON("Watermelon"),
    BLUE_RASPBERRY("Blue Raspberry"),
    LEMON_LIME("Lemon Lime"),
    GRAPE("Grape"),
    ORANGE("Orange"),
    GREEN_APPLE("Green Apple"),
    PINEAPPLE("Pineapple"),
    STRAWBERRY_KIWI("Strawberry Kiwi"),
    MANGO("Mango");

    private final String flavorName;
}
