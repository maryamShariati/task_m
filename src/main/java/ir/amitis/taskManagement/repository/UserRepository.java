package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByIdAndDeletedFalse(Long id);
    Optional<User> findByUsernameAndDeletedIsFalse(String username);

}
