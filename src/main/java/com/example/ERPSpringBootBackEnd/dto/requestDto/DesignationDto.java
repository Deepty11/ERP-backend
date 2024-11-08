package com.example.ERPSpringBootBackEnd.dto.requestDto;

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
    private SalaryRangeDto salaryRangeDto;

    public DesignationDto(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public DesignationDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
