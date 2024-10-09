package com.example.ERPSpringBootBackEnd.enums;

import java.util.Arrays;

public enum Role {
    ADMIN, USER;

    public static Role getRole(String roleString) {
        return Arrays.stream(Role.values())
                .filter(role -> role.name().equalsIgnoreCase(roleString))
                .findFirst()
                .orElse(null);
    }
}
