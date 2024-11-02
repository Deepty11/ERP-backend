package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.requestDto.UserDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.ErrorResponseDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.SuccessResponseDto;
import com.example.ERPSpringBootBackEnd.enums.DBState;
import com.example.ERPSpringBootBackEnd.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/add-user")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDto userDto) {

        DBState state = userService.save(userDto);

        return state.equals(DBState.ALREADY_EXIST)
                ? ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                        new ErrorResponseDto(
                                "User Already exist",
                                new Date().getTime(),
                                null))
                : ResponseEntity.ok().body(
                        new SuccessResponseDto(
                            "Saved Successfully",
                                    new Date().getTime()));
    }

    @GetMapping("/loggedInUser")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUserDtoByUsername(username));
    }
}