package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByIdAndDeletedFalse(Long id);
    Optional<User> findByUsernameAndDeletedIsFalse(String username);
    boolean  existsByUsernameAndDeletedIsFalse(String username);

}
