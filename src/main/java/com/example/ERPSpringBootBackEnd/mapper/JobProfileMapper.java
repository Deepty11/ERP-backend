package com.example.ERPSpringBootBackEnd.mapper;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.JobProfileDto;
import com.example.ERPSpringBootBackEnd.enums.DesignationLevel;
import com.example.ERPSpringBootBackEnd.enums.EmploymentType;
import com.example.ERPSpringBootBackEnd.model.JobProfile;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;

import java.util.Objects;

public class JobProfileMapper {
    public static JobProfileDto toDto(JobProfile jobProfile) {
        return JobProfileDto.builder()
                .employeeId(jobProfile.getEmployeeId())
                .level(Objects.nonNull(jobProfile.getLevel()) ? jobProfile.getLevel().getTitle() : null)
                .employmentType(Objects.nonNull(jobProfile.getEmploymentType()) ? jobProfile.getEmploymentType().getTitle() : null)
                .joiningDate(Objects.nonNull(jobProfile.getJoiningDate()) ? DateUtils.formatDate(jobProfile.getJoiningDate()) : null)
                .basicSalary(jobProfile.getBasicSalary())
                .compensation(jobProfile.getCompensation())
                .designationDto(DesignationMapper.toDto(jobProfile.getDesignation()))
                .build();
    }

    public static JobProfile toJobProfile(JobProfileDto jobProfileDto) {
        return JobProfile.builder()
                .employeeId(jobProfileDto.getEmployeeId())
                .employmentType(EmploymentType.getEmploymentType(jobProfileDto.getEmploymentType()))
                .level(DesignationLevel.getDesignationLevel(jobProfileDto.getLevel()))
                .basicSalary(jobProfileDto.getBasicSalary())
                .compensation(jobProfileDto.getCompensation())
                .build();
    }
}
