package com.meerity.yourgym.constants.protein;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProteinForms {
    POWDER("Powder"),
    BAR("Bar"),
    TABLET("Tablet"),
    LIQUID("Liquid"),
    OTHER("Other");

    private final String description;
}
