package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    Other("Other");

    String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Gender getGender(String genderString) {
        return Arrays.stream(Gender.values())
                .filter(r -> r.name.equalsIgnoreCase(genderString))
                .findFirst()
                .orElse(null);
    }

}