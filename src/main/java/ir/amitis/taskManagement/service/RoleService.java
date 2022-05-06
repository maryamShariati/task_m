package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.Ids;
import ir.amitis.taskManagement.dto.RoleGetDto;
import ir.amitis.taskManagement.dto.RoleSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Role;
import ir.amitis.taskManagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository repository;

    @Transactional
    public void save(RoleSaveDto roleDto) {
        repository.save(Role.fromDto(roleDto));
    }


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

    @Transactional
    public void updateRoleName(Long id, String name) throws RecordNotFoundException {
        var role = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        role.setName(name);
        repository.save(role);
    }
}
