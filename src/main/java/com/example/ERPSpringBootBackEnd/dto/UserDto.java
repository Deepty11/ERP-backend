package com.example.ERPSpringBootBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
    private String birthDate;
    private String gender;
    private String religion;
    private ContactInfoDto contactInfoDto;
    private EmergencyContactInfoDto emergencyContactInfoDto;


    // TODO: Add these fields later
//    @OneToOne
//    @JoinColumn(name = "user_document_id")
//    private UserDocument document;
//
//    @OneToOne
//    @JoinColumn(name = "job_info_id")
//    private JobInformation jobInformation;
//
//    @OneToMany(mappedBy = "user")
//    private List<LeaveInfo> leaveInfo;

    public UserDto(
            String firstName,
            String lastName,
            String username,
            String password,
            String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
