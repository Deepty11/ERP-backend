package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.SalaryRangeDto;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;

import java.util.Objects;

public class DesignationMapper {
    public static DesignationDto toDto(Designation designation) {
        DesignationDto designationDto = DesignationDto.builder()
                .title(designation.getTitle())
                .description(designation.getDescription())
                .build();
        if (!Objects.isNull(designation.getSalaryRange())) {
            designationDto.setSalaryRangeDto(
                    new SalaryRangeDto(
                            designation.getSalaryRange().getMin(),
                            designation.getSalaryRange().getMax()));
        }

        return designationDto;
    }

    public static Designation toDesignation(DesignationDto designationDto) {
        Designation designation = new Designation();
        designation.setTitle(designationDto.getTitle());
        designation.setDescription(designationDto.getDescription());

        if (designationDto.getSalaryRangeDto() != null) {
            designation.setSalaryRange(
                    new SalaryRange(
                            designationDto.getSalaryRangeDto().getMin(),
                            designationDto.getSalaryRangeDto().getMax()));
        }

        return designation;
    }
}
