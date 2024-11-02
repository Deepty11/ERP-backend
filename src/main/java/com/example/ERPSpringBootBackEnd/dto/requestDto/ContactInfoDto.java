package com.example.ERPSpringBootBackEnd.dto.requestDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoDto {
    private String mobileNumber;
    private String email;
    private String address;
}

