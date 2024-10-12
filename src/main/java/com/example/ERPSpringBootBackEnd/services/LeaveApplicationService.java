package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.dto.UserDto;
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

        if(Objects.isNull(user)) {
            return null;
        }

        LeaveApplication leaveApplication = LeaveApplication.builder()
                .id(leaveApplicationDto.getId())
                .leaveType(LeaveType.getLeaveType(leaveApplicationDto.getLeaveType()))
                .description(leaveApplicationDto.getDescription())
                .fromDate(DateUtils.parseDate(leaveApplicationDto.getFromDate()))
                .toDate(DateUtils.parseDate(leaveApplicationDto.getToDate()))
                .user(user)
                .build();
        return repository.save(leaveApplication);
    }

    public List<LeaveApplicationDto> getAllLeaveApplication() {
        return repository.findAll()
                .stream()
                .map(leaveApplication -> convertToDto(leaveApplication))
                .collect(Collectors.toList());
    }

    public LeaveApplicationDto convertToDto(LeaveApplication leaveApplication) {
        User user = leaveApplication.getUser();

        return new LeaveApplicationDto(
                leaveApplication.getId(),
                leaveApplication.getLeaveType().toString(),
                leaveApplication.getDescription(),
                DateUtils.formatDate(leaveApplication.getFromDate()),
                DateUtils.formatDate(leaveApplication.getToDate()),
                new UserDto(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getRole().toString()));
    }
}
