package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {


    List<Task> findTaskByUser_Username(String username);

    List<Task> getTaskByCreateAtAndName(LocalDateTime createAt, String name);
}
