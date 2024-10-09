package com.example.ERPSpringBootBackEnd.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoDto {
    private String mobileNumber;
    private String email;
    private AddressDto presentAddress;
    private AddressDto permanentAddress;
}

