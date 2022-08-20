package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Priority;
import ir.amitis.taskManagement.model.Task;
import ir.amitis.taskManagement.model.TaskType;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record TaskGetDto(

        @JsonProperty("name")String name,
        @JsonProperty("subject")String subject,
        @JsonProperty("priority") Priority priority,
        @JsonProperty("description") String description,
        @JsonProperty("type") List<TaskType> types,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
        @JsonProperty("creatAt") LocalDateTime creatAt
) {
        public TaskGetDto(String name, String subject, Priority priority, String description,
                          List<TaskType> types, LocalDateTime creatAt) {
                this.name = name;
                this.subject = subject;
                this.priority = priority;
                this.description = description;
                this.types = types;
                this.creatAt = creatAt;
        }
}
