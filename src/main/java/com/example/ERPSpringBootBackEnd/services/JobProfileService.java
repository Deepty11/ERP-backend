package com.example.ERPSpringBootBackEnd.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.EmergencyContactInfoDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.JobProfileDto;
import com.example.ERPSpringBootBackEnd.enums.DesignationLevel;
import com.example.ERPSpringBootBackEnd.enums.EmploymentType;
import com.example.ERPSpringBootBackEnd.enums.Relationship;
import com.example.ERPSpringBootBackEnd.mapper.DesignationMapper;
import com.example.ERPSpringBootBackEnd.mapper.JobProfileMapper;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.EmergencyContactInfo;
import com.example.ERPSpringBootBackEnd.model.JobProfile;
import com.example.ERPSpringBootBackEnd.repositories.JobProfileRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobProfileService {
    @Autowired
    private DesignationService designationService;

    @Autowired
    private JobProfileRepository repository;

    public JobProfile save(JobProfileDto jobProfileDto) {
        JobProfile jobProfile = JobProfileMapper.toJobProfile(jobProfileDto);

        if (jobProfileDto.getDesignationDto() != null) {
            Designation designation = designationService.getDesignationBy(
                    jobProfileDto.getDesignationDto().getId()).orElse(null);
            jobProfile.setDesignation(designation);
        }

        if (StringUtil.notNullNorEmpty(jobProfileDto.getJoiningDate())) {
            jobProfile.setJoiningDate(DateUtils.parseDate(jobProfileDto.getJoiningDate()));
        }

        repository.save(jobProfile);
        return jobProfile;
    }

    public JobProfile update(long id, JobProfileDto jobProfileDto) {
        Optional<JobProfile> optional = repository.findById(id);
        if(optional.isPresent()) {
            JobProfile jobProfileDB = optional.get();

            jobProfileDB.setEmployeeId(jobProfileDto.getEmployeeId());
            jobProfileDB.setBasicSalary(jobProfileDto.getBasicSalary());
            jobProfileDB.setCompensation(jobProfileDto.getCompensation());
            jobProfileDB.setLevel(DesignationLevel.getDesignationLevel(jobProfileDto.getLevel()));
            jobProfileDB.setEmploymentType(EmploymentType.getEmploymentType(jobProfileDto.getEmploymentType()));

            if (jobProfileDto.getDesignationDto() != null) {
                Designation designation = designationService.getDesignationBy(
                        jobProfileDto.getDesignationDto().getId()).orElse(null);
                jobProfileDB.setDesignation(designation);
            }

            if (StringUtil.notNullNorEmpty(jobProfileDto.getJoiningDate())) {
                jobProfileDB.setJoiningDate(DateUtils.parseDate(jobProfileDto.getJoiningDate()));
            }

            return repository.save(jobProfileDB);
        }

        return null;
    }
}
