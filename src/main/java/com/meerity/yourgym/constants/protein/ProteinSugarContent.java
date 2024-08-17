package com.meerity.yourgym.constants.protein;

import com.meerity.yourgym.constants.TextFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ProteinSugarContent implements TextFormatter {

    VERY_HIGH(BigDecimal.valueOf(20)),
    HIGH(BigDecimal.valueOf(15)),
    MEDIUM(BigDecimal.valueOf(10)),
    LOW(BigDecimal.valueOf(5)),
    VERY_LOW(BigDecimal.valueOf(1));

    private final BigDecimal percentage;

    @Override
    public String makePretty() {
        String name = this.name().toLowerCase();
        String[] words = name.split("_");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }

        return String.join(" ", words)  + " (" + percentage.toString() + " %)";
    }
}
