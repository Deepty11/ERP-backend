package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.dto.UserDto;
import com.example.ERPSpringBootBackEnd.enums.LeaveStatus;
import com.example.ERPSpringBootBackEnd.enums.LeaveType;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.repositories.LeaveApplicationRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository repository;

    @Autowired
    private UserService userService;

    public LeaveApplication save(LeaveApplicationDto leaveApplicationDto) {
        User user = userService.getUserByUsername(leaveApplicationDto.getUserDto().getUsername());

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
        return repository.save(leaveApplication);
    }

    public List<LeaveApplicationDto> getAllLeaveApplication() {
        return convertToDtoList(repository.findAll());
    }

    public List<LeaveApplicationDto> convertToDtoList(List<LeaveApplication> list) {
        return list
                .stream()
                .map(leaveApplication -> convertToDto(leaveApplication))
                .collect(Collectors.toList());
    }

    public LeaveApplicationDto convertToDto(LeaveApplication leaveApplication) {
        User user = leaveApplication.getUser();

        return new LeaveApplicationDto(
                leaveApplication.getId(),
                leaveApplication.getLeaveType().getTitle(),
                leaveApplication.getDescription(),
                DateUtils.formatDate(leaveApplication.getFromDate()),
                DateUtils.formatDate(leaveApplication.getToDate()),
                leaveApplication.getStatus().getTitle(),
                new UserDto(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getRole().toString()));
    }

    public List<LeaveApplicationDto> getApplicationsByUserId(String userId) {
        long id = Long.parseLong(userId);

        List<LeaveApplication> list = repository
                .findAll()
                .stream()
                .filter(leaveApplication ->
                        leaveApplication.getUser().getId() == id)
                .toList();

        return convertToDtoList(list);
    }
}
