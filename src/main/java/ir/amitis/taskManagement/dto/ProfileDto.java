package ir.amitis.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.amitis.taskManagement.customValidation.MobileNumber;
import ir.amitis.taskManagement.model.Profile;
import ir.amitis.taskManagement.model.Sex;

import javax.validation.constraints.*;
import java.util.Date;

public record ProfileDto(

        @Size(min = 3)
        @JsonProperty("name")String name,
        @Size(min = 3)
        @JsonProperty("surname")String surname,
        @JsonProperty("sex") Sex sex,
        @PastOrPresent
        @JsonProperty("birthday") Date birthday,
        @Email
        @JsonProperty("email")String email,
        @MobileNumber
        @JsonProperty("mobileNumber")String mobileNumber
) {

        public ProfileDto(String name, String surname,Sex sex, Date birthday, String email, String mobileNumber) {
                this.name = name;
                this.surname = surname;
                this.sex = sex;
                this.birthday = birthday;
                this.email = email;
                this.mobileNumber = mobileNumber;
        }

}
