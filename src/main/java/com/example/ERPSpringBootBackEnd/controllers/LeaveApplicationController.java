package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.requestDto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.LeaveOverviewDto;
import com.example.ERPSpringBootBackEnd.enums.LeaveStatus;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.services.LeaveApplicationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/leave")
public class LeaveApplicationController {
    @Autowired
    private LeaveApplicationService service;

    @PostMapping("/create-application")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<LeaveApplication> save(@RequestBody LeaveApplicationDto leaveApplicationDto) {
       LeaveApplication leaveApplication = service.save(leaveApplicationDto);

       return Objects.isNull(leaveApplication)
               ? ResponseEntity.notFound().build()
               : ResponseEntity.ok().body(leaveApplication);
    }

    @GetMapping("/leave-applications")
    @RolesAllowed({"ADMIN"})
    public  ResponseEntity<List<LeaveApplicationDto>> getAllLeaveApplications() {
        return ResponseEntity.ok().body(service.getAllLeaveApplication());
    }

    @GetMapping("/my-leave-applications")
    @RolesAllowed({"ADMIN", "USER"})
    public  ResponseEntity<List<LeaveApplicationDto>> getAllApplicationsByUserId(@RequestParam long userId) {
        return ResponseEntity.ok().body(service.getApplicationsByUserId(userId));
    }

    @GetMapping("/overview")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<LeaveOverviewDto> getLeaveOverview(@RequestParam long userId) {
        LeaveOverviewDto leaveOverviewDto = new LeaveOverviewDto(
                userId,
                service.totalAllowedCasualLeaves,
                service.totalAllowedSickLeaves,
                service.getNumberOfLeaves(service.getAllCasualLeavesByUserId(userId)),
                service.getNumberOfLeaves(service.getAllSickLeavesByUserId(userId)),
                service.getRemainingCasualLeaves(userId),
                service.getRemainingSickLeaves(userId)
                );
        System.out.println("Leave Application Dto: " + leaveOverviewDto);
        return ResponseEntity.ok().body(leaveOverviewDto);
    }

    @GetMapping("/action")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<String> action(
            @RequestParam long leaveId,
            @RequestParam boolean approve) {
        Optional<LeaveApplication> optional = service.getLeaveApplicationById(leaveId);
        if(optional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Leave Application is not found");
        }

        LeaveApplication leaveApplicationFromDB = optional.get();

        leaveApplicationFromDB.setStatus(approve ? LeaveStatus.APPROVED: LeaveStatus.DECLINED);

        LeaveApplication updatedApplication = service.saveLeaveApplication(leaveApplicationFromDB);

        return ResponseEntity.ok().body("Leave Application is updated!");
    }
}
