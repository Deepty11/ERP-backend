package com.example.ERPSpringBootBackEnd.dto;

import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;
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
    private SalaryRangeDto salaryRange;

    public DesignationDto(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public DesignationDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Designation convertToDesignation() {
        Designation designation = new Designation();
        designation.setTitle(this.getTitle());
        designation.setDescription(getDescription());

        if (getSalaryRange() != null) {
            designation.setSalaryRange(new SalaryRange(getSalaryRange().getMin(), getSalaryRange().getMax()));
        }

        return designation;
    }
}
