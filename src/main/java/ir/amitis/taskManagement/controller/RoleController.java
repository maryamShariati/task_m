package ir.amitis.taskManagement.controller;

import ir.amitis.taskManagement.dto.Ids;
import ir.amitis.taskManagement.dto.RoleGetDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @ResponseBody
    public List<RoleGetDto> getAllRoleById(@RequestBody @Valid Ids id) throws RecordNotFoundException {
        return roleService.getAllRoleById(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RoleGetDto getRoleById(@PathVariable Long id)throws RecordNotFoundException{
     var role= roleService.getById(id);
        return new RoleGetDto(role.name());
    }

    @PatchMapping("/{id}/{roleName}")
    @Validated
    @Secured("ROLE_UPDATE_ROLE")
    public void updateRoleName(@PathVariable @Positive Long id, @PathVariable @NotNull @NotBlank String roleName) throws RecordNotFoundException {
        roleService.updateRoleName(id, roleName);
    }
}
