package com.example.ERPSpringBootBackEnd.mapper;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.TaskDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.UserDto;
import com.example.ERPSpringBootBackEnd.enums.TaskPriority;
import com.example.ERPSpringBootBackEnd.enums.TaskStatus;
import com.example.ERPSpringBootBackEnd.model.Task;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.services.UserService;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


public class TaskMapper {
    public static Task toTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(Objects.isNull(taskDto.getStatus())
                        ? TaskStatus.TO_DO // by default will set TO_DO
                        : TaskStatus.getTaskStatus(taskDto.getStatus()))
                .priority(Objects.isNull(taskDto.getPriority())
                        ? TaskPriority.LOW // by default will set LOW
                        : TaskPriority.getTaskPriority(taskDto.getPriority()))
                .taskAllowance(taskDto.getTaskAllowance())
                .build();
        return task;
    }

    public static TaskDto toTaskDto(Task task) {
        return new TaskDto(task);
    }

}
