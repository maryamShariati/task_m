package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.TaskSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Task;
import ir.amitis.taskManagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository repository;
    private final UserService userService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(TaskSaveDto taskDto)throws RecordNotFoundException {
        var user = userService.getByUsername(taskDto.username()).orElseThrow(RecordNotFoundException::new);
        repository.save(Task.taskFromDto(taskDto,user));
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);

    }

    @Transactional(readOnly = true)
    public List<Task> getTaskByUsername(String username) {
        return repository.findTaskByUser_Username(username);
    }


    @Transactional(readOnly = true)
    public List<Task> getTaskByCreatAtAndUsername(LocalDateTime createAt, String username) {
        return repository.getTaskByCreateAtAndName(createAt, username);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Long id) throws RecordNotFoundException {
        Optional<Task> task = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException()));
        if (task.isPresent()) {
            var task1 = task.get();
            task1.setDeleteAt(LocalDateTime.now());
            repository.save(task1);
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateDescriptionById(Long id, String description) throws RecordNotFoundException {
        Optional<Task> task = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException()));
        if (task.isPresent()) {
            var update = task.get();
            update.setDescription(String.valueOf(description));
            repository.save(update);
        }
    }


}
