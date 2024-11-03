package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.dto.requestDto.DesignationDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.JobProfileDto;
import com.example.ERPSpringBootBackEnd.enums.DesignationLevel;
import com.example.ERPSpringBootBackEnd.enums.EmploymentType;
import com.example.ERPSpringBootBackEnd.mapper.DesignationMapper;
import com.example.ERPSpringBootBackEnd.mapper.JobProfileMapper;
import com.example.ERPSpringBootBackEnd.model.Designation;
import com.example.ERPSpringBootBackEnd.model.JobProfile;
import com.example.ERPSpringBootBackEnd.repositories.JobProfileRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (!jobProfileDto.getJoiningDate().isEmpty()) {
            jobProfile.setJoiningDate(DateUtils.parseDate(jobProfileDto.getJoiningDate()));
        }

        repository.save(jobProfile);
        return jobProfile;
    }
}
