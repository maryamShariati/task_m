package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record UserPostDto(
        @NotBlank
        @Min(4)
        @JsonProperty("username")String username,
        @NotBlank
        @Min(8)
        @JsonProperty("password")String password) {
}
