package ir.amitis.taskManagement.repository;

import ir.amitis.taskManagement.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> getAllByNameIn(List<String> roleNames);
}
