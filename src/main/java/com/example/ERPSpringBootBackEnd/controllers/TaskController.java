package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.dto.requestDto.TaskDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.ErrorResponseDto;
import com.example.ERPSpringBootBackEnd.dto.responseDto.SuccessResponseDto;
import com.example.ERPSpringBootBackEnd.model.Task;
import com.example.ERPSpringBootBackEnd.services.TaskService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/create-task")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<?> addTask(@RequestBody TaskDto taskDto) {
        Task task = taskService.save(taskDto);
        return Objects.isNull(task)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ErrorResponseDto("Error in saving task", new Date().getTime(), null))
                : ResponseEntity.ok().body(task);

    }

    @GetMapping("/tasks")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<?> getAllTaskDtos() {
        List<TaskDto> taskDtos = taskService.getAllTaskDtos();
        return ResponseEntity.ok().body(taskDtos);
    }

    @GetMapping("/getTaskDetails")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<?> getTaskDtoById(@RequestParam long id) {
        TaskDto taskDto = taskService.getTaskDetailsById(id);
        return Objects.nonNull(taskDto)
                ? ResponseEntity.ok().body(taskDto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        "No Task found with this Id",
                        new Date().getTime(),
                        null));

    }

    @PostMapping("/edit-task")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<?> updateTask(@RequestParam long id,
                                        @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        return Objects.nonNull(updatedTask)
                ? ResponseEntity.ok().body(new SuccessResponseDto(
                        "Updated Task Successfully",
                new Date().getTime()))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        "No Task found with the Id "+ id,
                        new Date().getTime(),
                        null));
    }
}
