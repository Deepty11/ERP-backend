package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.repositories.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private SalaryService salaryService;

    public Designation save(DesignationDto designationDto) {
        Designation designation =  new Designation();
        designation.setTitle(designationDto.getTitle());
        designation.setDescription(designationDto.getDescription());

        if(designationDto.getSalaryRange() != null) {
            designation.setSalaryRange(
                    salaryService.save(designationDto.getSalaryRange()));
        }

        return designationRepository.save(designation);
    }

    public List<DesignationDto> getAllDesignations() {
        List<Designation> designationsFromDB = getDesignationList();

        return designationsFromDB.stream().map(designation -> new DesignationDto(
                designation.getId(),
                designation.getTitle(),
                designation.getDescription())).collect(Collectors.toList());
    }

    public List<Designation> getDesignationList() {
        return designationRepository.findAll();
    }

    public Optional<Designation> getDesignationBy(long id) {
        return designationRepository.findById(id);
    }
}
