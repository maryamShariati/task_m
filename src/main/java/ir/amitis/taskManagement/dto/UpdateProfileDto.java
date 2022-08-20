package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Sex;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public record UpdateProfileDto(
        @JsonProperty("name") String name,
        @JsonProperty("surname") String surname,
        @JsonProperty("sex") Sex sex,
        @JsonProperty("email") String email,
        @JsonProperty("birthday") Date birthday,
        @JsonFormat(pattern = "yyyy/mm/yy")
        @JsonProperty("mobileNumber") String mobileNumber,
        @NotBlank
        @JsonProperty("username") String username

) {
}
