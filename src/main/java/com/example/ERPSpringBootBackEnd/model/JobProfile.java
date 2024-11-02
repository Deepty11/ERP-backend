package com.example.ERPSpringBootBackEnd.model;

import com.example.ERPSpringBootBackEnd.dto.requestDto.JobProfileDto;
import com.example.ERPSpringBootBackEnd.enums.Currency;
import com.example.ERPSpringBootBackEnd.enums.DesignationLevel;
import com.example.ERPSpringBootBackEnd.enums.EmploymentType;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotNull
    @Column(unique = true)
    private String employeeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joiningDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    private DesignationLevel level;

    private double compensation;
    private double basicSalary;

//    private double conveyanceAllowance;
//    private double medicalReimbursement;
//    private double houseRent;

//    @NotNull
    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @Transient
    private String joinningDateString;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Version
    private int version = 0;

    public String getJoiningDateString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(joiningDate);
    }

//    private String getFormattedAllowance(double amount) {
//        return this.getCurrency().toString() + amount;
//    }
//
//    public String getBasicSalaryString() {
//        return getFormattedAllowance(this.basicSalary);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobProfile that = (JobProfile) o;
        return id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public JobProfileDto convertToDto() {
        return JobProfileDto.builder()
                .employeeId(employeeId)
                .employmentType(employmentType.getTitle())
                .level(level.getTitle())
                .joiningDate(DateUtils.formatDate(joiningDate))
                .basicSalary(basicSalary)
                .compensation(compensation)
                .designationDto(designation.convertToDto())
                .build();
    }
}