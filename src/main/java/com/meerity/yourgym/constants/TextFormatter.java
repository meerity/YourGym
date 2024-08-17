package com.meerity.yourgym.constants;

public interface TextFormatter {

    default String makePretty() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase();
    }
}
