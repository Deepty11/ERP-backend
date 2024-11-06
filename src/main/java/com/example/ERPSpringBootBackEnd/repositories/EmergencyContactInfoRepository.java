package com.example.ERPSpringBootBackEnd.repositories;

import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactInfoRepository extends JpaRepository<EmergencyContactInfo, Long> {
}
