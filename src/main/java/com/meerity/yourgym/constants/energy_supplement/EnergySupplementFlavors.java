package com.meerity.yourgym.constants.energy_supplement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnergySupplementFlavors {
    LEMON("Lemon"),
    RASPBERRY("Raspberry"),
    MANGO("Mango"),
    PASSION_FRUIT("Passion fruit"),
    WATERMELON("Watermelon"),
    BLUEBERRY("Blueberry"),
    ORANGE("Orange"),
    GRAPE("Grape"),
    APPLE("Apple"),
    PEACH("Peach");

    private final String flavorName;
}

