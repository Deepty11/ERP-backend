package com.example.ERPSpringBootBackEnd.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryRangeDto {
    double min;
    double max;

    public SalaryRangeDto(double min, double max) {
        this.min = min;
        this.max = max;
    }
}
