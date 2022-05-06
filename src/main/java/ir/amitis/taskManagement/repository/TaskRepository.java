package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
}
