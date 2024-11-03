package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;

public class ContactInfoMapper {
    public static ContactInfoDto toDto(ContactInfo contactInfo) {
        return ContactInfoDto.builder()
                .mobileNumber(contactInfo.getMobileNumber())
                .email(contactInfo.getEmail())
                .address(contactInfo.getAddress())
                .build();
    }

}
