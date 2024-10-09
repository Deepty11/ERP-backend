package com.example.ERPSpringBootBackEnd.enums;

import java.util.Arrays;

public enum Religion {
    ISLAM, HINDUISM, BUDDHISM, CHRISTIANITY;

    public static Religion getReligion( String religionString) {
        return Arrays.stream(Religion.values())
                .filter(r -> r.name().equalsIgnoreCase(religionString))
                .findFirst()
                .orElse(null);
    }
}

