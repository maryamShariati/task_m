package ir.amitis.taskManagement.config;


import ir.amitis.taskManagement.model.Profile;
import ir.amitis.taskManagement.model.Role;
import ir.amitis.taskManagement.model.User;
import ir.amitis.taskManagement.repository.RoleRepository;
import ir.amitis.taskManagement.repository.UserRepository;
import ir.amitis.taskManagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var roles = generateRoleName();
        try {
            roleRepository.saveAll(roles);
        } catch (Exception ignore) {
            ignore.printStackTrace();
//            System.out.println("roles have saved .");
        }

        try {
            if (!userRepository.existsByUsernameAndDeletedIsFalse("administrator")) {
                User user = new User();
                Profile profile = new Profile();

                user.setUsername("administrator");
                user.setPassword(passwordEncoder.encode("123456789"));
                user.setDeleted(false);
                user.setLocked(false);
                user.setProfile(profile);
                userRepository.save(user);

                List<Role> roleList = (List<Role>) roleRepository.findAll();

                roleService.addRoleByUser(user, roleList);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Role> generateRoleName() {
        return List.of(
                new Role(1, "ROLE_GET_USER", "GET", "USER"),
                new Role(2, "ROLE_UPDATE_USER", "UPDATE", "USER"),
                new Role(3, "ROLE_DELETE_USER", "DELETE", "USER"),
                new Role(4, "ROLE_GET_ROLE", "GET", "ROLE"),
                new Role(5, "ROLE_ADD_ROLE", "CREATE", "USER_ROLE"),
                new Role(6, "ROLE_GET_PROFILE", "GET", "PROFILE"),
                new Role(7, "ROLE_UPDATE_PROFILE", "UPDATE", "PROFILE"),
                new Role(8, "ROLE_ADD_TASK", "CREATE", "TASK"),
                new Role(9, "ROLE_GET_TASK", "GET", "TASK"),
                new Role(10, "ROLE_UPDATE_TASK", "UPDATE", "TASK"),
                new Role(11, "ROLE_DELETE_TASK", "DELETE", "TASK")
        );
    }
}
