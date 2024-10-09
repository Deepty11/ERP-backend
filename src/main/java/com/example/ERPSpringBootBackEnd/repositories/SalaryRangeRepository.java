package com.example.ERPSpringBootBackEnd.repositories;

import com.example.ERPSpringBootBackEnd.model.SalaryRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRangeRepository extends JpaRepository<SalaryRange, Long> {
}
