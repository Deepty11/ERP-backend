package com.example.ERPSpringBootBackEnd.model;

import com.example.ERPSpringBootBackEnd.enums.LeaveType;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeaveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "leaveInfoSeq",
            sequenceName = "leaveInfoSeq",
            allocationSize = 1)
    @GeneratedValue(generator = "leaveInfoSeq")
    private long id;

    @NotNull(message = "Please select a type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private String description;

    @NotNull(message = "From field cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;

    @NotNull(message = "To field cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Version
    private int version = 0;

    public long getNumberOfDays() {
        return DateUtils.getIntervalInDays(this.fromDate, this.toDate);
    }

    public String getDateRangeString() {
        return DateUtils.getDateRangeString(this.fromDate, this.toDate);
    }

    public String getRequestedDate() {
        return DateUtils.formatDate(this.created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeaveInfo)) return false;
        LeaveInfo leaveInfo = (LeaveInfo) o;
        return id == leaveInfo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
