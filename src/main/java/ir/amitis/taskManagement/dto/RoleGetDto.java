package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RoleGetDto(
        @JsonProperty("name")
        String name
) {
    public RoleGetDto(String name) {
        this.name = name;
    }

    public static RoleGetDto roleGetDto(Role role){
        RoleGetDto roleGetDto=new RoleGetDto(role.getName());
        return roleGetDto;
    }
}
