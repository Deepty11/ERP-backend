package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.DesignationDto;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.services.DesignationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designation")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @PostMapping("/add-designation")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Designation> saveDesignation(@RequestBody DesignationDto designationDto) {
        System.out.println("DesignationDTo: "+ designationDto);
        return ResponseEntity.ok().body(designationService.save(designationDto));
    }

    @GetMapping("/designations")
    public ResponseEntity<List<DesignationDto>> getDesignationList() {
        return ResponseEntity.ok().body(designationService.getAllDesignations());
    }
}
