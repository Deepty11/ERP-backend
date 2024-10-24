package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.dto.LeaveOverviewDto;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.services.LeaveApplicationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/applications")
    @RolesAllowed({"ADMIN"})
    public  ResponseEntity<List<LeaveApplicationDto>> getAllLeaveApplications(@RequestParam(required = false) Long userId) {
        return Objects.isNull(userId)
                ? ResponseEntity.ok().body(service.getAllLeaveApplication())
                : ResponseEntity.ok().body(service.getApplicationsByUserId(userId));
    }

    @GetMapping("/overview")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<LeaveOverviewDto> getLeaveOverview(@RequestParam Long userId) {
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

//    @GetMapping("/my-applications")
//    @RolesAllowed({"ADMIN", "USER"})
//    public ResponseEntity<List<LeaveApplicationDto>> getLeaveApplicationsByUserId(@RequestParam long userId) {
//        return ResponseEntity.ok().body(service.getLeaveApplicationsByUserId(userId));
//
//    }
}
