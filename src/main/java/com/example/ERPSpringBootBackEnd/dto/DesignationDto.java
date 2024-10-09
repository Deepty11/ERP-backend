package com.example.ERPSpringBootBackEnd.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignationDto {
    private long id;
    private String title;
    private String description;
    private String employmentType;
    private String level;
    private SalaryRangeDto salaryRange;

    public DesignationDto(long id, String title, String description, String employmentType, String level) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employmentType = employmentType;
        this.level = level;
    }
}
