package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskStatus {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public static TaskStatus getTaskStatus(String statusString) {
        return Arrays.stream(TaskStatus.values())
                .filter(
                        taskStatus -> taskStatus.name().equals(statusString))
                .findFirst()
                .orElse(null);
    }
}
