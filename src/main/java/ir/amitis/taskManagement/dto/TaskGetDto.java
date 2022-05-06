package ir.amitis.taskManagement.dto;

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
        @NotBlank
        @NotNull
        @JsonProperty("name")String name,
        @NotBlank
        @NotNull
        @JsonProperty("subject")String subject,
        @NotNull
        @JsonProperty("priority") Priority priority,
        @NotNull
        @NotBlank
        @JsonProperty("description") String description,

        @NotEmpty
        @JsonProperty("type") List<TaskType> types,

        @FutureOrPresent
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

        public static TaskGetDto taskGetDto(Task task){
                TaskGetDto taskGetDto=new TaskGetDto(task.getName(),task.getSubject(),task.getTaskPriority(),
                        task.getDescription(),task.getTaskTypes(),task.getCreateAt());
                return taskGetDto;
        }
}
