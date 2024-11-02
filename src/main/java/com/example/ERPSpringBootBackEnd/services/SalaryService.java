package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.SalaryRangeDto;
import com.example.ERPSpringBootBackEnd.model.SalaryRange;
import com.example.ERPSpringBootBackEnd.repositories.SalaryRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    @Autowired
    private SalaryRangeRepository repository;

    public SalaryRange save(SalaryRangeDto salaryRangeDto) {
        SalaryRange salaryRange = new SalaryRange(salaryRangeDto.getMin(), salaryRangeDto.getMax());
        repository.save(salaryRange);
        return salaryRange;
    }
}
