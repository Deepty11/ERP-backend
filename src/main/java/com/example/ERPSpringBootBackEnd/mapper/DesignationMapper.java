package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;

import java.util.Objects;

public class DesignationMapper {
    public static DesignationDto toDto(Designation designation) {
        DesignationDto designationDto = DesignationDto.builder()
                .title(designation.getTitle())
                .description(designation.getDescription())
                .build();
        if (!Objects.isNull(designationDto.getSalaryRange())) {
            designation.setSalaryRange(
                    new SalaryRange(
                            designationDto.getSalaryRange().getMin(),
                            designationDto.getSalaryRange().getMax()));
        }

        return designationDto;
    }

    public static Designation toDesignation(DesignationDto designationDto) {
        Designation designation = new Designation();
        designation.setTitle(designationDto.getTitle());
        designation.setDescription(designationDto.getDescription());

        if (designationDto.getSalaryRange() != null) {
            designation.setSalaryRange(
                    new SalaryRange(
                            designationDto.getSalaryRange().getMin(),
                            designationDto.getSalaryRange().getMax()));
        }

        return designation;
    }
}
