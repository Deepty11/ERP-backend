package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import com.example.ERPSpringBootBackEnd.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {
    @Autowired
    private ContactInfoRepository contactInfoRepository;

    public ContactInfo save(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = ContactInfo.builder()
                .email(contactInfoDto.getEmail())
                .mobileNumber(contactInfoDto.getMobileNumber())
                .address(contactInfoDto.getAddress())
                .build();
        contactInfoRepository.save(contactInfo);
        return contactInfo;
    }
}
