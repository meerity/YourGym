package com.meerity.yourgym.constants.protein;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProteinFlavors {
    CHOCOLATE("Chocolate"),
    VANILLA("Vanilla"),
    STRAWBERRY("Strawberry"),
    BANANA("Banana"),
    PEANUT_BUTTER("Peanut Butter"),
    CARAMEL("Caramel"),
    COFFEE("Coffee"),
    BLUEBERRY("Blueberry"),
    RASPBERRY("Raspberry"),
    COCONUT("Coconut"),
    ORANGE_CREAM("Orange Cream"),
    CINNAMON_SWIRL("Cinnamon Swirl"),
    MOCHA("Mocha"),
    APPLE_PIE("Apple Pie"),
    MAPLE_SYRUP("Maple Syrup"),
    LEMON_CAKE("Lemon Cake"),
    HONEY("Honey"),
    MANGO("Mango");

    private final String flavorName;
}
