package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;

import java.util.List;

public class LeaveApplicationMapper {
    public static LeaveApplicationDto toLeaveApplicationDto(LeaveApplication leaveApplication) {
        return new LeaveApplicationDto(leaveApplication);
    }

    public static LeaveApplication toLeaveApplication(LeaveApplicationDto leaveApplicationDto) {
        return new LeaveApplication(leaveApplicationDto);
    }

    public static List<LeaveApplicationDto> toListOfLeaveApplicationDto(List<LeaveApplication> leaveApplications) {
        return leaveApplications
                .stream()
                .map(LeaveApplicationMapper::toLeaveApplicationDto)
                .toList();
    }

    public static List<LeaveApplication> toListOfLeaveApplication(List<LeaveApplicationDto> leaveApplicationDtos) {
        return leaveApplicationDtos
                .stream()
                .map(LeaveApplicationMapper::toLeaveApplication)
                .toList();
    }
}
