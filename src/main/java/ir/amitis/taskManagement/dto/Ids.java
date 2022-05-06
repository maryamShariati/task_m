package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public record Ids(
        @NotEmpty
        @Positive
        @JsonProperty("ids") List<Long> ids) {
}
