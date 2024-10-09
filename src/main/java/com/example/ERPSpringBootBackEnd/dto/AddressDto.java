package com.example.ERPSpringBootBackEnd.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String houseNo;
    private String road;
    private String thana;
    private String city;
    private String district;
    private String postalCode;
}
