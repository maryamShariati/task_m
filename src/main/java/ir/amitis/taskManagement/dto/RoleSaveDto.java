package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RoleSaveDto(@NotBlank
                          @NotNull
                          @JsonProperty("main")
                          String main,

                          @NotBlank
                          @NotNull
                          @JsonProperty("category")
                          String category,

                          @NotBlank
                          @NotNull
                          @JsonProperty("name")
                          String name) {

}
