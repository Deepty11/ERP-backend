package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.EmergencyContactInfoDto;
import com.example.ERPSpringBootBackEnd.enums.Relationship;
import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;

import java.util.Objects;

public class EmergencyContactInfoMapper {
    public static EmergencyContactInfoDto toDto(EmergencyContactInfo emergencyContactInfo) {
        return EmergencyContactInfoDto.builder()
                .name(emergencyContactInfo.getName())
                .mobileNumber(emergencyContactInfo.getMobileNumber())
                .relation(Objects.isNull(emergencyContactInfo.getRelation()) ? null : emergencyContactInfo.getRelation().name())
                .build();
    }

    public static EmergencyContactInfo toEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) {
        return EmergencyContactInfo.builder()
                .name(emergencyContactInfoDto.getName())
                .relation(Relationship.getRelationship(emergencyContactInfoDto.getRelation()))
                .mobileNumber(emergencyContactInfoDto.getMobileNumber())
                .build();
    }
}
