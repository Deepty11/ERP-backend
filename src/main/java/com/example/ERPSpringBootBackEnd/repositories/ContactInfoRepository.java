package com.example.ERPSpringBootBackEnd.repositories;

import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}
