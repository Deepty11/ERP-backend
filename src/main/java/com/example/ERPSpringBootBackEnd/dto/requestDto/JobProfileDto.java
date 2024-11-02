package com.example.ERPSpringBootBackEnd.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JobProfileDto {
    @NotNull
    private String employeeId;
    private String employmentType;
    private String level;
    private String joiningDate;
    private double basicSalary;
    private double compensation;
    private DesignationDto designationDto;
}
