package ir.amitis.taskManagement.model;

import ir.amitis.taskManagement.dto.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false,columnDefinition = "varchar(25)")
    private String name;

    @Column(nullable = false,columnDefinition = "varchar(25)")
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
    private String email;
    @Column(nullable = false)
    private Date birthday;
    @Column(columnDefinition = "varchar(11)")
    private String MobileNumber;
    private boolean deleted;

    @OneToOne(optional = false)
    private User user;



    public static Profile profileFromDto(ProfileDto profileDto){
        Profile profile=new Profile();
        profile.setName(profileDto.name());
        profile.setSurname(profileDto.surname());
        profile.setSex((profileDto.sex()));
        profile.setBirthday(profileDto.birthday());
        return profile;
    }


    @Override
    public boolean equals(Object object){
        if (object==null ||this.getClass() != object.getClass()){
            return false;
        }
        if (this.getId() != null){
            Profile profile= (Profile) object;
            return this.getId().equals(profile.getId());
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", user=" + user +
                '}';
    }
}
