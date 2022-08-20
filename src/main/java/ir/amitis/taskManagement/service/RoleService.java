package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.Ids;
import ir.amitis.taskManagement.dto.RoleGetDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Role;
import ir.amitis.taskManagement.model.User;
import ir.amitis.taskManagement.model.UserRole;
import ir.amitis.taskManagement.repository.RoleRepository;
import ir.amitis.taskManagement.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository repository;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;

    public List<RoleGetDto> getAllRoleById(Ids id) throws RecordNotFoundException {
        List<Role> roles = (List<Role>) repository.findAllById(id.ids());
        if (!roles.isEmpty()) {
            List<RoleGetDto> roleGetDto = new ArrayList<>();
            roles.forEach(role -> roleGetDto.add(RoleGetDto.roleGetDto(role)));
            return roleGetDto;
        }
        throw new RecordNotFoundException();
    }

    public RoleGetDto getById(Long id) throws RecordNotFoundException {
        var role = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        var roleDto = RoleGetDto.roleGetDto(role);
        return roleDto;
    }
    /**
     * in: userId: 1, role names: ADMIN, USER
     * q: select * from role where name in ('ADMIN', 'USER')
     * @return
     */


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addRoleByUsername(String username, List<String> roleNames) {
        userService.getByUsername(username).ifPresent(user -> repository.getAllByNameIn(roleNames).forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            userRoleRepository.save(userRole);
        }));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addRoleByUser(User user, List<Role> roles) {
        roles.forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);
        });


}
}
