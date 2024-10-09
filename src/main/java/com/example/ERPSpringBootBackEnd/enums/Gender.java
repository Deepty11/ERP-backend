package com.example.ERPSpringBootBackEnd.enums;

import java.util.Arrays;

public enum Gender {
    MALE, FEMALE, Other;

//    public static List<String> getGenderList() {
//        List<String> genderList = new ArrayList<>();
//        for (Gender g: Gender.values()) {
//            genderList.add(g.fullString);
//        }
//
//        return genderList;
//    }

    public static Gender getGender( String genderString) {
        return Arrays.stream(Gender.values())
                .filter(r -> r.name().equalsIgnoreCase(genderString))
                .findFirst()
                .orElse(null);
    }

}