package com.example.ERPSpringBootBackEnd.repositories;

import com.example.ERPSpringBootBackEnd.model.JobProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {
}
