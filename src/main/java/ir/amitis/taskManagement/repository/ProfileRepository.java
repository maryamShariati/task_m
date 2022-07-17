package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile,Long> {

    @Query(value = "from Profile p where p.user.username= :username")
    Optional<Profile> findProfileByUsername(@Param("username") String username);
}
