package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.services.LeaveApplicationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public  ResponseEntity<List<LeaveApplicationDto>> getAllLeaveApplications(@RequestParam(required = false) String userId) {
        return Objects.isNull(userId)
                ? ResponseEntity.ok().body(service.getAllLeaveApplication())
                : ResponseEntity.ok().body(service.getApplicationsByUserId(userId));
    }
}
