package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserPostDto(
        @NotBlank
        @Size(min = 4)
        @JsonProperty("username")String username,
        @NotBlank
        @Size(min = 8)
        @JsonProperty("password")String password) {
}
