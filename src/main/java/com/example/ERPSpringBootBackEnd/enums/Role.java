package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    ADMIN("Admin"),
    USER("User");

    String name;

    Role(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return name;
//    }

    public static Role getRole(String roleString) {
        return Arrays.stream(Role.values())
                .filter(role -> role.name.equalsIgnoreCase(roleString))
                .findFirst()
                .orElse(null);
    }
}
