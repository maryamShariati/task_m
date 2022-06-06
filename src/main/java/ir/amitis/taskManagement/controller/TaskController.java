package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.TaskGetDto;
import ir.amitis.taskManagement.dto.TaskSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Task;
import ir.amitis.taskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping("")
    public void save(@RequestBody @Valid TaskSaveDto taskDto) {
        service.save(taskDto);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) throws RecordNotFoundException {
        Task task = service.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping("/get")
    @ResponseBody
    @Validated
    public List<TaskGetDto> getTaskByUsername(@RequestParam(name = "username") @NotBlank String username) {
        return service.getTaskByUsername(username).stream().map(name -> new TaskGetDto(name.getName()
                , name.getSubject(), name.getTaskPriority(), name.getDescription(), name.getTaskTypes()
                , name.getCreateAt())).collect(Collectors.toList());
    }


    @GetMapping
    public List<TaskGetDto> getTaskByCreatAtAndUsername(@RequestBody Map<String, Object> income) {
        var username = income.get("username");
        var creatAt = income.get("creatAt");
        return service.getTaskByCreatAtAndUsername((LocalDateTime) creatAt, (String) username).stream()
                .map(task -> new TaskGetDto(task.getName(), task.getSubject(), task.getTaskPriority()
                        , task.getDescription(), task.getTaskTypes(), task.getCreateAt())).collect(Collectors.toList());
    }


    @PutMapping("/update/{id}")
    public void updateDescription(@PathVariable Long id, @RequestParam String description)
            throws RecordNotFoundException {
        service.updateDescriptionById(id, description);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
    }





}
