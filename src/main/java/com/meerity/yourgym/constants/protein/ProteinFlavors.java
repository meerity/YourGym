package com.meerity.yourgym.constants.protein;

import com.meerity.yourgym.constants.TextFormatter;

public enum ProteinFlavors implements TextFormatter {
    CHOCOLATE,
    VANILLA,
    STRAWBERRY,
    BANANA,
    PEANUT_BUTTER,
    CARAMEL,
    COFFEE,
    BLUEBERRY,
    RASPBERRY,
    COCONUT,
    ORANGE_CREAM,
    CINNAMON_SWIRL,
    MOCHA,
    APPLE_PIE,
    MAPLE_SYRUP,
    LEMON_CAKE,
    HONEY,
    MANGO;

    @Override
    public String makePretty() {
        String name = this.name().toLowerCase();
        String[] words = name.split("_");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }

        return String.join(" ", words);
    }
}
