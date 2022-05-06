package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.model.Profile;
import ir.amitis.taskManagement.model.Sex;

import javax.validation.constraints.*;
import java.util.Date;

public record ProfileDto(
        @NotBlank
        @NotNull
        @Min(3)
        @JsonProperty("name")String name,
        @NotBlank
        @Min(3)
        @JsonProperty("surname")String surname,
        @NotBlank
        @JsonProperty("sex") Sex sex,
        @PastOrPresent
        @JsonProperty("birthday") Date birthday,
        @Email
        @JsonProperty("email")String email,
        @NotBlank
        @Max(11)
        @JsonProperty("phoneNumber")String phoneNumber
) {

        public ProfileDto(String name, String surname,Sex sex, Date birthday, String email, String phoneNumber) {
                this.name = name;
                this.surname = surname;
                this.sex = sex;
                this.birthday = birthday;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        public static ProfileDto profileDto(Profile profile){
                ProfileDto profileDto=new ProfileDto(profile.getName(), profile.getName(),
                        profile.getSex(), profile.getBirthday(), profile.getEmail(), profile.getPhoneNumber());
                return profileDto;
        }
}
