package com.example.ERPSpringBootBackEnd.services;
import com.example.ERPSpringBootBackEnd.dto.requestDto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.EmergencyContactInfoDto;
import com.example.ERPSpringBootBackEnd.enums.Relationship;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;
import com.example.ERPSpringBootBackEnd.repositories.EmergencyContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyContactInfoService {
    @Autowired
    private EmergencyContactInfoRepository emergencyContactInfoRepository;

    public EmergencyContactInfo save (EmergencyContactInfoDto emergencyContactInfoDto) {
        EmergencyContactInfo emergencyContactInfo = EmergencyContactInfo.builder()
                .name(emergencyContactInfoDto.getName())
                .mobileNumber(emergencyContactInfoDto.getMobileNumber())
                .relation(Relationship.getRelationship(emergencyContactInfoDto.getRelation()))
                .build();

        return emergencyContactInfoRepository.save(emergencyContactInfo);
    }

    public EmergencyContactInfo update(long id, EmergencyContactInfoDto emergencyContactInfoDto) {
        Optional<EmergencyContactInfo> optional = emergencyContactInfoRepository.findById(id);
        if(optional.isPresent()) {
            EmergencyContactInfo emergencyContactInfoFromDB = optional.get();
            emergencyContactInfoFromDB.setName(emergencyContactInfoDto.getName());
            emergencyContactInfoFromDB.setRelation(Relationship.getRelationship(emergencyContactInfoDto.getRelation()));
            emergencyContactInfoFromDB.setMobileNumber(emergencyContactInfoDto.getMobileNumber());

            return emergencyContactInfoRepository.save(emergencyContactInfoFromDB);
        }

        return null;
    }

}
