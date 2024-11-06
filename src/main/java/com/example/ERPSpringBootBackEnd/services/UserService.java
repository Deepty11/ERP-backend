package com.example.ERPSpringBootBackEnd.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.ContactInfoDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.EmergencyContactInfoDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.UserDto;
import com.example.ERPSpringBootBackEnd.enums.DBState;
import com.example.ERPSpringBootBackEnd.enums.Gender;
import com.example.ERPSpringBootBackEnd.enums.Religion;
import com.example.ERPSpringBootBackEnd.mapper.ContactInfoMapper;
import com.example.ERPSpringBootBackEnd.mapper.UserMapper;
import com.example.ERPSpringBootBackEnd.model.ContactInfo;
import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;
import com.example.ERPSpringBootBackEnd.model.JobProfile;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.repositories.UserRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
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

    @Autowired
    private EmergencyContactInfoService emergencyContactInfoService;

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

    public User updateUserDetailsForId(long id, UserDto userDto) {
        User userFromDB = getUserById(id);
        if(Objects.isNull(userFromDB)) {
            return null;
        }

        userFromDB.setFirstName(userDto.getFirstName());
        userFromDB.setLastName(userDto.getLastName());
        userFromDB.setGender(Gender.getGender(userDto.getGender()));
        userFromDB.setReligion(Religion.getReligion(userDto.getReligion()));
        if(StringUtil.notNullNorEmpty(userDto.getBirthDate())) {
            userFromDB.setBirthDate(DateUtils.parseDate(userDto.getBirthDate()));
        }

        // update contactInfo
        if(Objects.nonNull(userDto.getContactInfoDto())) {
            ContactInfoDto contactInfoDto = userDto.getContactInfoDto();

            ContactInfo contactInfo = null;
            if(Objects.isNull(userFromDB.getContactInfo())) {
                contactInfo = contactInfoService.save(contactInfoDto);
            } else {
                contactInfo = contactInfoService.update(userFromDB.getContactInfo().getId(), contactInfoDto);
            }

            if(Objects.isNull(contactInfo)) {
                return null;
            }

            userFromDB.setContactInfo(contactInfo);
        }

        // update emergencyContactInfo
        if(Objects.nonNull(userDto.getEmergencyContactInfoDto())) {
            EmergencyContactInfoDto emergencyContactInfoDto = userDto.getEmergencyContactInfoDto();

            EmergencyContactInfo emergencyContactInfo = null;
            if(Objects.isNull(userFromDB.getEmergencyContact())) {
                emergencyContactInfo = emergencyContactInfoService.save(emergencyContactInfoDto);
            } else {
                emergencyContactInfo = emergencyContactInfoService.update(userFromDB.getEmergencyContact().getId(), emergencyContactInfoDto);
            }

            if(Objects.isNull(emergencyContactInfo)) {
                return null;
            }

            userFromDB.setEmergencyContact(emergencyContactInfo);
        }

        userFromDB.setId(id);
        return userRepository.save(userFromDB);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}