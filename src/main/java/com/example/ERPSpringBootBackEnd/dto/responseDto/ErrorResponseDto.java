package com.example.ERPSpringBootBackEnd.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String mesage;
    private long timestamp;
    private Map<String, String> fieldErrors = new HashMap<>();
}
