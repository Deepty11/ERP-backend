package com.example.ERPSpringBootBackEnd.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponseDto {
    String message;
    long timestamp;
}
