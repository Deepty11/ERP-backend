package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmploymentType {
    FULL_TIME("Full-Time"),
    PART_TIME("Part-Time"),
    CONTRACT("Contract");

    String title;

    EmploymentType(String title) {
        this.title = title;
    }

    public static EmploymentType getEmploymentType(String employmentTypeString) {
        return Arrays.stream(EmploymentType.values())
                .filter(employmentType -> employmentType.title.equalsIgnoreCase(employmentTypeString))
                .findFirst()
                .orElse(null);
    }
}
