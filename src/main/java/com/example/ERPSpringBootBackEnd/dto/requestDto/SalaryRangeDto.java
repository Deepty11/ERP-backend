package com.example.ERPSpringBootBackEnd.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalaryRangeDto {
    double min;
    double max;

    public SalaryRangeDto(double min, double max) {
        this.min = min;
        this.max = max;
    }
}
