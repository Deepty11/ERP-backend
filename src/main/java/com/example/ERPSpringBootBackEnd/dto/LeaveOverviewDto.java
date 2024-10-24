package com.example.ERPSpringBootBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Data
public class LeaveOverviewDto {
    private long userId;
    private int totalCasualLeave;
    private int totalSickLeave;
    private int numberOfCasualLeavesTaken;
    private int numberOfSickLeavesTaken;
    private int remainingCasualLeaves;
    private int remainingSickLeaves;
}
