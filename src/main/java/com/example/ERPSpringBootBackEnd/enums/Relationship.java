package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Relationship {
    PARENTS("Parents"),
    SPOUSE("Spouse"),
    CHILDREN("Children"),
    OTHER("Other");

    String title;


    Relationship(String relation) {
        this.title = relation;
    }

    public static List<String> getRelationshipList() {
        List<String> relationshipList = new ArrayList<>();
        for (Relationship r: Relationship.values()) {
            relationshipList.add(r.title);
        }

        return relationshipList;
    }

    public static Relationship getRelationship( String relationShipString) {
        return Arrays.stream(Relationship.values())
                .filter(r -> r.title.equalsIgnoreCase(relationShipString))
                .findFirst()
                .orElse(null);

    }
}

