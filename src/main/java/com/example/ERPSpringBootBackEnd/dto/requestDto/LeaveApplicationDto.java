package com.example.ERPSpringBootBackEnd.dto.requestDto;
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
    private String status;
    private UserDto userDto;
}
