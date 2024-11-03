package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;

import java.util.List;

public class LeaveApplicationMapper {
    public static LeaveApplicationDto toLeaveApplicationDto(LeaveApplication leaveApplication) {
        return new LeaveApplicationDto(leaveApplication);
    }

    public static List<LeaveApplicationDto> toListOfLeaveApplicationDto(List<LeaveApplication> leaveApplications) {
        return leaveApplications
                .stream()
                .map(LeaveApplicationMapper::toLeaveApplicationDto)
                .toList();
    }
}
