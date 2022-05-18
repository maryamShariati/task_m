package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.DescriptionDto;
import ir.amitis.taskManagement.dto.TaskGetDto;
import ir.amitis.taskManagement.dto.TaskSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Task;
import ir.amitis.taskManagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository repository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(TaskSaveDto taskDto)  {

        repository.save(Task.taskFromDto(taskDto));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Long id) throws RecordNotFoundException {
        Optional<Task> task= Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException()));
        if (task.isPresent()){
            var task1=task.get();
            task1.setDeleteAt(LocalDateTime.now());
            repository.save(task1);
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateDescriptionById(Long id, DescriptionDto descriptionDto) throws RecordNotFoundException {
        Optional<Task> task= Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException()));
        if (task.isPresent()) {
            var update = task.get();
            update.setDescription(String.valueOf(descriptionDto));
            repository.save(update);
        }
    }

    @Transactional(readOnly = true)
    public TaskGetDto getTaskById(Long id) throws RecordNotFoundException {
        var task = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        var taskDto = TaskGetDto.taskGetDto(task);
        return taskDto;
    }


}
