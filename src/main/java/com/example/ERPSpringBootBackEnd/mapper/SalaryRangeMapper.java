package com.example.ERPSpringBootBackEnd.mapper;
import com.example.ERPSpringBootBackEnd.dto.requestDto.SalaryRangeDto;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;

public class SalaryRangeMapper {
    public static SalaryRangeDto toSalaryRangeDto(SalaryRange salaryRange) {
        return new SalaryRangeDto(salaryRange.getMin(), salaryRange.getMax());
    }
}
