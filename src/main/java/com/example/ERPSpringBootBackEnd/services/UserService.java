package com.example.ERPSpringBootBackEnd.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.*;
import com.example.ERPSpringBootBackEnd.enums.DBState;
import com.example.ERPSpringBootBackEnd.enums.Gender;
import com.example.ERPSpringBootBackEnd.enums.Religion;
import com.example.ERPSpringBootBackEnd.mapper.ContactInfoMapper;
import com.example.ERPSpringBootBackEnd.mapper.UserMapper;
import com.example.ERPSpringBootBackEnd.model.*;
import com.example.ERPSpringBootBackEnd.repositories.FileEntityRepository;
import com.example.ERPSpringBootBackEnd.repositories.UserRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Autowired
    private FileEntityRepository fileEntityRepository;

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

        // update jobProfile
        if(Objects.nonNull(userDto.getJobProfileDto())) {
            JobProfileDto jobProfileDto = userDto.getJobProfileDto();

            JobProfile jobProfile = null;
            if(Objects.isNull(userFromDB.getEmergencyContact())) {
                jobProfile = jobProfileService.save(jobProfileDto);
            } else {
                jobProfile = jobProfileService.update(userFromDB.getJobProfile().getId(), jobProfileDto);
            }

            if(Objects.isNull(jobProfile)) {
                return null;
            }

            userFromDB.setJobProfile(jobProfile);
        }

        userFromDB.setId(id);
        return userRepository.save(userFromDB);
    }

    public void deleteUserById(long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with the Id" + id);
        }

        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = (String) principle;
        return getUserByUsername(username);
    }

    public List<User> getAllUsersByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    public UserDto uploadProfilePicture(long id, FileEntityDto fileEntityDto) throws IOException {
        Optional<User> optional = userRepository.findById(id);

        if(optional.isEmpty()) {
            return null;
        }

        User userFromDB = optional.get();

        if(Objects.nonNull(userFromDB.getProfilePicture())) {
           FileEntity fileEntityFromDB = userFromDB.getProfilePicture();
           fileEntityFromDB.setFileName(fileEntityDto.getFileName());
           fileEntityFromDB.setDocument(Base64.getDecoder().decode(fileEntityDto.getData()));
           fileEntityRepository.save(fileEntityFromDB);
           userFromDB.setProfilePicture(fileEntityFromDB);

        } else {
            FileEntity newProfilePicture = new FileEntity();
            newProfilePicture.setFileName(fileEntityDto.getFileName());
            newProfilePicture.setDocument(Base64.getDecoder().decode(fileEntityDto.getData()));
            fileEntityRepository.save(newProfilePicture);
            userFromDB.setProfilePicture(newProfilePicture);
        }

        User user = userRepository.save(userFromDB);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}