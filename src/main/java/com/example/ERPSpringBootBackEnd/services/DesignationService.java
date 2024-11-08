package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.SalaryRangeDto;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;
import com.example.ERPSpringBootBackEnd.repositories.DesignationRepository;
import com.example.ERPSpringBootBackEnd.repositories.SalaryRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryRangeRepository salaryRangeRepository;

    public Designation save(DesignationDto designationDto) {
        Designation designation =  new Designation();
        designation.setTitle(designationDto.getTitle());
        designation.setDescription(designationDto.getDescription());

        if(designationDto.getSalaryRangeDto() != null) {
            designation.setSalaryRange(
                    salaryService.save(designationDto.getSalaryRangeDto()));
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

    public Designation update(long id, DesignationDto designationDto) {
        Optional<Designation> optional = getDesignationBy(id);
        if(optional.isEmpty()) {
            return null;
        }

        Designation designationFromDB = optional.get();
        designationFromDB.setTitle(designationDto.getTitle());
        designationFromDB.setDescription(designationDto.getDescription());

        SalaryRangeDto salaryRangeDto = designationDto.getSalaryRangeDto();

        if(Objects.nonNull(salaryRangeDto)) {
            SalaryRange newSalaryRange = null;

            if(Objects.isNull(designationFromDB.getSalaryRange())) {
                // create new salaryRange
                newSalaryRange = salaryService.save(salaryRangeDto);
                newSalaryRange.setDesignation(designationFromDB);
                System.out.println(newSalaryRange.getMax() + newSalaryRange.getMin() + "\n\n");
            } else {
                // update SalaryRange
                newSalaryRange = salaryService.getSalaryRangeById(designationFromDB.getSalaryRange().getId());
                newSalaryRange.setMax(salaryRangeDto.getMax());
                newSalaryRange.setMin(salaryRangeDto.getMin());
            }

            salaryRangeRepository.save(newSalaryRange);

            designationFromDB.setSalaryRange(newSalaryRange);
        }

        return designationRepository.save(designationFromDB);
    }
}
