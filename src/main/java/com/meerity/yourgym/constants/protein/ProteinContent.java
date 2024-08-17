package com.meerity.yourgym.constants.protein;

import com.meerity.yourgym.constants.TextFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ProteinContent implements TextFormatter {
    LOW(BigDecimal.valueOf(5)),
    MEDIUM(BigDecimal.valueOf(15)),
    HIGH(BigDecimal.valueOf(30)),
    HIGHEST(BigDecimal.valueOf(50)),;

    private final BigDecimal percentage;

    @Override
    public String makePretty() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase() + " (" + percentage.toString() + "%)";
    }
}