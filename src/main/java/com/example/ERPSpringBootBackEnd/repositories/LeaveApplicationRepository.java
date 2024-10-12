package com.example.ERPSpringBootBackEnd.repositories;

import com.example.ERPSpringBootBackEnd.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
}
