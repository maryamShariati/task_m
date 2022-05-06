package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile,Long> {
}
