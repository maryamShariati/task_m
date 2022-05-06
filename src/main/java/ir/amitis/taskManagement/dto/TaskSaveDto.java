package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Priority;
import ir.amitis.taskManagement.model.TaskType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

public record TaskSaveDto(
        @NotBlank
        @NotNull
        @JsonProperty("name")String name,
        @NotBlank
        @NotNull
        @JsonProperty("subject")String subject,
        @NotNull
        @JsonProperty("priority")Priority priority,

        @NotNull
        @NotBlank
        @JsonProperty("description") String description,

        @NotEmpty
        @JsonProperty("type")List<TaskType> types
        ) {
}
