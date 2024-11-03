package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.enums.LeaveStatus;
import com.example.ERPSpringBootBackEnd.enums.LeaveType;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.repositories.LeaveApplicationRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import com.example.ERPSpringBootBackEnd.mapper.LeaveApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository repository;

    @Autowired
    private UserService userService;

    public int totalAllowedSickLeaves = 3;
    public int totalAllowedCasualLeaves = 14;

    public LeaveApplication save(LeaveApplicationDto leaveApplicationDto) {
        User user = userService.getUserByUsername(
                leaveApplicationDto.getUserDto().getUsername());

        if (Objects.isNull(user)) {
            return null;
        }

        LeaveApplication leaveApplication = LeaveApplication.builder()
                .id(leaveApplicationDto.getId())
                .leaveType(LeaveType.getLeaveType(leaveApplicationDto.getLeaveType()))
                .description(leaveApplicationDto.getDescription())
                .fromDate(DateUtils.parseDate(leaveApplicationDto.getFromDate()))
                .toDate(DateUtils.parseDate(leaveApplicationDto.getToDate()))
                .status(LeaveStatus.getLeaveStatus(leaveApplicationDto.getStatus()))
                .user(user)
                .build();
        return saveLeaveApplication(leaveApplication);
    }

    public LeaveApplication saveLeaveApplication(LeaveApplication leaveApplication) {
        return repository.save(leaveApplication);
    }

    public List<LeaveApplicationDto> getAllLeaveApplication() {
        return LeaveApplicationMapper.toListOfLeaveApplicationDto(repository.findAll());
    }

    public List<LeaveApplicationDto> getAllSickLeavesByUserId(long userId) {
        return getAllLeaves(userId, LeaveType.SICK);
    }

    public List<LeaveApplicationDto> getAllCasualLeavesByUserId(long userId) {
        return getAllLeaves(userId, LeaveType.CASUAL);
    }

    public List<LeaveApplicationDto> getAllLeaves(long userId, LeaveType leaveType) {
        List<LeaveApplicationDto> leaves = getApplicationsByUserId(userId);
        return leaves
                .stream()
                .filter((leaveApplicationDto ->
                        leaveApplicationDto.getLeaveType().equals(leaveType.getTitle())
                ))
                .toList();
    }

    public int getNumberOfLeaves(List<LeaveApplicationDto> leaveApplicationDtos) {
        return leaveApplicationDtos.size();
    }

    public int getRemainingSickLeaves(long userId) {
        return totalAllowedSickLeaves - getNumberOfLeaves(getAllSickLeavesByUserId(userId));
    }

    public int getRemainingCasualLeaves(long userId) {
        return totalAllowedCasualLeaves - getNumberOfLeaves(getAllCasualLeavesByUserId(userId));
    }

    public List<LeaveApplicationDto> getApplicationsByUserId(long userId) {
        List<LeaveApplicationDto> allLeavesDto = getAllLeaveApplication();
        return allLeavesDto.stream().filter((leaveApplicationDto ->
                        leaveApplicationDto.getUserDto().getId() == userId
                ))
                .toList();
    }

    public Optional<LeaveApplication> getLeaveApplicationById(long id) {
        return repository.findById(id);
    }
}
