package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.AuthenticationRequest;
import com.example.ERPSpringBootBackEnd.dto.AuthenticationResponse;
import com.example.ERPSpringBootBackEnd.exception.AuthenticationFailedException;
import com.example.ERPSpringBootBackEnd.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws AuthenticationFailedException {
        String token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(new AuthenticationResponse(token));
    }
}
