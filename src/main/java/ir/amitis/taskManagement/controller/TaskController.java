package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.TaskGetDto;
import ir.amitis.taskManagement.dto.TaskSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Task;
import ir.amitis.taskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public void save(@RequestBody @Valid TaskSaveDto taskDto)throws RecordNotFoundException  {
        service.save(taskDto);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Secured("ROLE_GET_TASK")
    public ResponseEntity<TaskGetDto> getTaskById(@PathVariable Long id) throws RecordNotFoundException {
        var task = service.getTaskById(id);
        TaskGetDto taskGetDto = new TaskGetDto(task.getName(), task.getSubject()
        , task.getTaskPriority(),task.getDescription(), task.getTaskTypes()
        , task.getCreateAt());
        return ResponseEntity.ok().body(taskGetDto);

    }

    @GetMapping("/by-username")
    @ResponseBody
    @Secured("ROLE_GET_TASK")
    @Validated
    public List<TaskGetDto> getTaskByUsername(@RequestParam(name = "username") @NotBlank String username) {
        return service.getTaskByUsername(username).stream().map(name -> new TaskGetDto(name.getName()
                , name.getSubject(), name.getTaskPriority(), name.getDescription(), name.getTaskTypes()
                , name.getCreateAt())).collect(Collectors.toList());
    }


    @GetMapping("/get-by-create")
    @Secured("ROLE_GET_TASK")
    public List<TaskGetDto> getTaskByCreatAtAndUsername(@RequestBody Map<String, Object> income) {
        var username = income.get("username");
        var creatAt = income.get("creatAt");
        return service.getTaskByCreatAtAndUsername((LocalDateTime) creatAt, (String) username).stream()
                .map(task -> new TaskGetDto(task.getName(), task.getSubject(), task.getTaskPriority()
                , task.getDescription(), task.getTaskTypes(), task.getCreateAt())).collect(Collectors.toList());
    }


    @PutMapping("/update/{id}")
    @Secured("ROLE_UPDATE")
    public void updateDescription(@PathVariable Long id, @RequestBody Map<String, String> description)
        throws RecordNotFoundException {
        service.updateDescriptionById(id,description.get("description"));
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_TASK")
    public void deleteById(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
    }


}
