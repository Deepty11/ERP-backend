package com.example.ERPSpringBootBackEnd.dto.requestDto;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.enums.TaskPriority;
import com.example.ERPSpringBootBackEnd.enums.TaskStatus;
import com.example.ERPSpringBootBackEnd.model.Task;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private long id;
    private String title;
    private String description;
    private String startDate;
    private String dueDate;
    private UserDto reportedBy;
    private List<UserDto> assignees;
    private String status;
    private String priority;
    private Double taskAllowance;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();

        if(StringUtil.notNullNorEmpty(DateUtils.formatDate(task.getStartDate()))) {
            this.startDate = DateUtils.formatDate(task.getStartDate());
        }

        if(StringUtil.notNullNorEmpty(DateUtils.formatDate(task.getDueDate()))) {
            this.dueDate = DateUtils.formatDate(task.getDueDate());
        }

        /**
         * setting task status as `TO_DO` and priority as `LOW` by default
         */
        this.status = Objects.isNull(task.getStatus())
                ? TaskStatus.TO_DO.name()
                : task.getStatus().name();
        this.priority = Objects.isNull(task.getPriority())
                ? TaskPriority.LOW.name()
                : task.getPriority().name();
        this.taskAllowance = task.getTaskAllowance();
    }
}
