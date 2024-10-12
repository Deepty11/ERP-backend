package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.LeaveApplicationDto;
import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import com.example.ERPSpringBootBackEnd.services.LeaveApplicationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class LeaveApplicationController {
    @Autowired
    private LeaveApplicationService service;

    @PostMapping("/add-leaveApplication")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<LeaveApplication> save(@RequestBody LeaveApplicationDto leaveApplicationDto) {
       LeaveApplication leaveApplication = service.save(leaveApplicationDto);

       return Objects.isNull(leaveApplication)
               ? ResponseEntity.notFound().build()
               : ResponseEntity.ok().body(leaveApplication);
    }
}
