package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.ErrorResponseDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.SuccessResponseDto;
import com.example.ERPSpringBootBackEnd.mapper.DesignationMapper;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.services.DesignationService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<DesignationDto>> getDesignationList() {
        return ResponseEntity.ok().body(designationService.getAllDesignations());
    }

    @GetMapping("/designation-details")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> getDesignationById(@RequestParam long id) {
        Optional<Designation> optional = designationService.getDesignationBy(id);
        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponseDto(
                            "No designation found with this id.",
                            new Date().getTime(),
                            null));
        }

        return ResponseEntity.ok().body(DesignationMapper.toDto(optional.get()));
    }

    @PostMapping("/edit-designation")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> updateDesignationById(@RequestParam long id,
                                                   @RequestBody DesignationDto designationDto) {
        Designation designation = designationService.update(id, designationDto);
        System.out.println("designation salary: ");
        System.out.println(designation.getSalaryRange().getMax());
        if(Objects.isNull(designation)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("No designation found with this id",
                            new Date().getTime(),
                            null));
        }

        return ResponseEntity
                .ok()
                .body(new SuccessResponseDto("Designation is updated successfully",
                        new Date().getTime()));

    }
}
