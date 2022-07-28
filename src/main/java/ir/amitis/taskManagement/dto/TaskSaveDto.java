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
        @JsonProperty("name")String name,
        @NotBlank
        @JsonProperty("subject")String subject,
        @JsonProperty("priority")Priority priority,
        @NotBlank
        @JsonProperty("description") String description,

        @NotEmpty
        @JsonProperty("type")List<TaskType> types
        ) {
}
