package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Religion {
    ISLAM("Islam"),
    HINDUISM("Hinduism"),
    BUDDHISM("Buddhism"),
    CHRISTIANITY("Christianity");

    String name;

    Religion(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return this.name;
//    }

    public static Religion getReligion(String religionString) {
        return Arrays.stream(Religion.values())
                .filter(r -> r.name.equalsIgnoreCase(religionString))
                .findFirst()
                .orElse(null);
    }
}

