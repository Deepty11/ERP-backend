package com.example.ERPSpringBootBackEnd.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskPriority {
    HIGH("High"),
    LOW("Low"),
    MEDIUM("Medium");

    String title;

    TaskPriority(String title) {
        this.title = title;
    }

    public static TaskPriority getTaskPriority(String taskPriorityString) {
        return Arrays.stream(TaskPriority.values())
                .filter(
                        taskPriority -> taskPriority.name().equals(taskPriorityString))
                .findFirst()
                .orElse(null);
    }
}
