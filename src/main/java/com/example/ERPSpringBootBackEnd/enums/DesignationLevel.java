package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DesignationLevel {
    JUNIOR("Junior"),
    SENIOR("Senior"),
    MID_LEVEL("Mid-Level"),
    EXECUTIVE("Executive"),
    MANAGER("Manager");

    String title;

    DesignationLevel(String title) {
        this.title = title;
    }

    public static DesignationLevel getDesignationLevel(String designationLevelString) {
        return Arrays.stream(DesignationLevel.values())
                .filter(designationLevel -> designationLevel
                        .title
                        .equalsIgnoreCase(designationLevelString))
                .findFirst()
                .orElse(null);
    }
}
