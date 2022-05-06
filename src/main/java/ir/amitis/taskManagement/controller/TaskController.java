package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.DescriptionDto;
import ir.amitis.taskManagement.dto.TaskGetDto;
import ir.amitis.taskManagement.dto.TaskSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public TaskGetDto getById(@PathVariable Long id) throws RecordNotFoundException {
       return service.getTaskById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
    }

    @PutMapping("/update/{id}")
    public void updateDescription(@PathVariable Long id,@RequestBody @Valid DescriptionDto descriptionDto) throws RecordNotFoundException {
        service.updateDescriptionById(id,descriptionDto);
    }




}
