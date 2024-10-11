package com.example.ERPSpringBootBackEnd.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.dto.JobProfileDto;
import com.example.ERPSpringBootBackEnd.dto.UserDto;
import com.example.ERPSpringBootBackEnd.enums.Gender;
import com.example.ERPSpringBootBackEnd.enums.Religion;
import com.example.ERPSpringBootBackEnd.enums.Role;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.JobProfile;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.repositories.ContactInfoRepository;
import com.example.ERPSpringBootBackEnd.repositories.DesignationRepository;
import com.example.ERPSpringBootBackEnd.repositories.JobProfileRepository;
import com.example.ERPSpringBootBackEnd.repositories.UserRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Autowired
    private DesignationRepository designationRepository;

    public User save(UserDto userDto) throws ParseException {
        if (userRepository.existsUserByUserName(userDto.getUsername())) {
            System.out.println("User with the username already exists");
            return null;
        }

        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGender(Gender.getGender(userDto.getGender()));
        user.setReligion(Religion.getReligion(userDto.getReligion()));
        user.setRole(Role.getRole(userDto.getRole()));

        if (userDto.getContactInfoDto() != null) {
            user.setContactInfo(mapToContactInfo(userDto.getContactInfoDto()));
        }

        if (userDto.getJobProfileDto() != null) {
            user.setJobProfile(mapToJobProfile(userDto.getJobProfileDto()));
        }

        if (StringUtil.notNullNorEmpty(userDto.getBirthDate())) {
            System.out.println("Birthdate: " + userDto.getBirthDate());
            user.setBirthDate(DateUtils.convertToDate(userDto.getBirthDate()));
        }

        return userRepository.save(user);
    }

    private ContactInfo mapToContactInfo(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = ContactInfo.builder()
                .email(contactInfoDto.getEmail())
                .mobileNumber(contactInfoDto.getMobileNumber())
                .address(contactInfoDto.getAddress())
                .build();
        contactInfoRepository.save(contactInfo);
        return contactInfo;
    }

    private JobProfile mapToJobProfile(JobProfileDto jobProfileDto) {
        JobProfile jobProfile = JobProfile.builder()
                .employeeId(jobProfileDto.getEmployeeId())
                .employmentType(jobProfileDto.getEmploymentType())
                .level(jobProfileDto.getLevel())
                .basicSalary(jobProfileDto.getBasicSalary())
                .compensation(jobProfileDto.getCompensation())
                .build();

        if (jobProfileDto.getDesignationDto() != null) {
            Designation designation = jobProfileDto.getDesignationDto().convertToDesignation();
            designationRepository.save(designation);
            jobProfile.setDesignation(designation);
        }
        if (!jobProfileDto.getJoiningDate().isEmpty()) {
            jobProfile.setJoiningDate(DateUtils.parseDate(jobProfileDto.getJoiningDate()));
        }

        jobProfileRepository.save(jobProfile);
        return jobProfile;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> user.convertToDto()).collect(Collectors.toList());
    }

    public User getUserById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}


class ResourceNotFoundException extends Exception {
    String message;

    ResourceNotFoundException(String message) {
        this.message = message;
    }
}
