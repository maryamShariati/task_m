package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PasswordDto(

        @NotBlank
        @NotNull
        @Min(8)
        @JsonProperty ("password")String password) {
}
