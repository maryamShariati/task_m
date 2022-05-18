package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
        @JsonProperty ("username") String username) {

    public UserDto(String username) {
        this.username = username;
    }
}



