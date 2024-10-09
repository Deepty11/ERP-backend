package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

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
}
