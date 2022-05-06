package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record DescriptionDto(
        @NotBlank
        @JsonProperty("description")
        String Description) {
}
