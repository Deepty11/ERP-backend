package com.example.ERPSpringBootBackEnd.dto;
import com.example.ERPSpringBootBackEnd.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplicationDto {
    private long id;
    private String leaveType;
    private String description;
    private String fromDate;
    private String toDate;
    private UserDto userDto;
}
