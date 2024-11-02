package com.example.ERPSpringBootBackEnd.dto.requestDto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmergencyContactInfoDto {
    private String name;
    private String relation;
    private String mobileNumber;

//    public EmergencyContactInfoDto(String name, String relation, String mobileNumber) {
//        this.name = name;
//        this.relation = relation;
//        this.mobileNumber = mobileNumber;
//    }
}
