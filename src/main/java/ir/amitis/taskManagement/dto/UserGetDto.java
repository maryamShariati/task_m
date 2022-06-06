package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserGetDto(@NotBlank
                         @NotNull
                         @Min(4)
                         @JsonProperty("username") String username) {

    public UserGetDto(String username) {
        this.username = username;
    }
}
