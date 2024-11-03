package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.*;
import com.example.ERPSpringBootBackEnd.enums.*;
import com.example.ERPSpringBootBackEnd.model.*;
import com.example.ERPSpringBootBackEnd.repositories.UserRepository;
import com.example.ERPSpringBootBackEnd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobProfileService jobProfileService;

    @Autowired
    private ContactInfoService contactInfoService;

    @Transactional
    public DBState save(UserDto userDto) {
        if (userRepository.existsUserByUserName(userDto.getUsername())) {
            System.out.println("User with the username already exists");
            return DBState.ALREADY_EXIST;
        }

        User user = UserMapper.toUser(userDto);

        if (userDto.getContactInfoDto() != null) {
            user.setContactInfo(contactInfoService.save(userDto.getContactInfoDto()));
        }

        if (userDto.getJobProfileDto() != null) {
            JobProfile jobProfile = jobProfileService.save(userDto.getJobProfileDto());
            user.setJobProfile(jobProfile);
        }

        userRepository.save(user);
        return DBState.SAVED;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toListOfUserDto(users);
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUserName(username).get();
    }

    public UserDto getUserDtoByUsername(String username) {
        User user = getUserByUsername(username);
        return Objects.isNull(user) ? null : new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getRole().toString());
    }

    public UserDto getUserDetailsForId(long id) {
        User user = userRepository.findById(id).orElse(null);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}