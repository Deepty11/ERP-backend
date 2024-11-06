package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import com.example.ERPSpringBootBackEnd.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ContactInfo update(long id, ContactInfoDto contactInfoDto) {
        Optional<ContactInfo> optional = contactInfoRepository.findById(id);
        if(optional.isPresent()) {
            ContactInfo contactInfoFromDB = optional.get();
            contactInfoFromDB.setAddress(contactInfoDto.getAddress());
            contactInfoFromDB.setEmail(contactInfoDto.getEmail());
            contactInfoFromDB.setAddress(contactInfoDto.getAddress());

            return contactInfoRepository.save(contactInfoFromDB);
        }

        return null;
    }
}
