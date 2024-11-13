package com.example.ERPSpringBootBackEnd.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.ERPSpringBootBackEnd.dto.requestDto.TaskDto;
import com.example.ERPSpringBootBackEnd.dto.requestDto.UserDto;
import com.example.ERPSpringBootBackEnd.mapper.TaskMapper;
import com.example.ERPSpringBootBackEnd.mapper.UserMapper;
import com.example.ERPSpringBootBackEnd.model.Task;
import com.example.ERPSpringBootBackEnd.model.User;
import com.example.ERPSpringBootBackEnd.repositories.TaskRepository;
import com.example.ERPSpringBootBackEnd.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task save(TaskDto taskDto) {
        Task task = TaskMapper.toTask(taskDto);

        System.out.println("LLLLL task" + task.getStatus());

        if (StringUtil.notNullNorEmpty(taskDto.getStartDate())) {
            task.setDueDate(DateUtils.parseDate(taskDto.getStartDate()));
        }

        if (StringUtil.notNullNorEmpty(taskDto.getDueDate())) {
            task.setDueDate(DateUtils.parseDate(taskDto.getDueDate()));
        }

        if (!taskDto.getAssignees().isEmpty()) {
            task.setAssignees(getUserSet(taskDto.getAssignees()));
        }

        if (Objects.nonNull(taskDto.getReportedBy())) {
            task.setReportedBy(userService.getUserById(taskDto.getReportedBy().getId()));
        }
        return taskRepository.save(task);
    }

    private List<User> getUserSet(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(userDto ->
                        userService.getUserById(userDto.getId()))
                .toList();
    }

    public List<TaskDto> getAllTaskDtos() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(task -> {
                    TaskDto taskDto = TaskMapper.toTaskDto(task);

                    if(Objects.nonNull(task.getReportedBy())) {
                        taskDto.setReportedBy(UserMapper.toUserDto(userService.getUserById(task.getReportedBy().getId())));
                    }

                    if(!task.getAssignees().isEmpty()) {
                        taskDto.setAssignees(UserMapper.toListOfUserDto(task.getAssignees()));
                    }

                    return taskDto;
                })
                .toList();
    }
}
