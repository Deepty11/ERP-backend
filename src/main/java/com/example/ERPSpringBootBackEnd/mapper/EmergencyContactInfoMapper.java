package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.EmergencyContactInfoDto;
import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;

public class EmergencyContactInfoMapper {
    public static EmergencyContactInfoDto toDto(EmergencyContactInfo emergencyContactInfo) {
        return EmergencyContactInfoDto.builder()
                .name(emergencyContactInfo.getName())
                .mobileNumber(emergencyContactInfo.getMobileNumber())
                .relation(emergencyContactInfo.getRelation().getTitle())
                .build();
    }
}
