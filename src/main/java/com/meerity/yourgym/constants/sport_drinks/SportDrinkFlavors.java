package com.meerity.yourgym.constants.sport_drinks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SportDrinkFlavors {
    LEMON("Lemon"),
    ORANGE("Orange"),
    GRAPE("Grape"),
    WATERMELON("Watermelon"),
    BERRY("Berry"),
    APPLE("Apple"),
    MANGO("Mango"),
    PINEAPPLE("Pineapple"),
    LIME("Lime"),
    PEACH("Peach");

    private final String flavorName;

}
