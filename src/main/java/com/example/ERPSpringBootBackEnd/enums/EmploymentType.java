package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

@Getter
public enum EmploymentType {
    FULL_TIME("Full-Time"),
    PART_TIME("Part-Time"),
    CONTRACT("Contract");

    String title;

    EmploymentType(String title) {
        this.title = title;
    }
}
