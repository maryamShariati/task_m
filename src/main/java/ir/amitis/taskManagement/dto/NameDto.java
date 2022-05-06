package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NameDto(
        @JsonProperty("name")String name
) {
}
