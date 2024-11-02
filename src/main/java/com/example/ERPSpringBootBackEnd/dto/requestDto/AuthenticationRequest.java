package com.example.ERPSpringBootBackEnd.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotEmpty(message = "{required.field}")
    @Email(message = "{invalid.field}")
    String username;

    @NotEmpty(message = "{required.field}")
    String password;

    public String getUsername() {
        return username;
    }

}
