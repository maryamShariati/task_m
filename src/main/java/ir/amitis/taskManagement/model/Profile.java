package ir.amitis.taskManagement.model;

import ir.amitis.taskManagement.dto.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String email;
    private Date birthday;
    private String mobileNumber;
    private boolean deleted;

    @OneToOne(optional = false, mappedBy = "profile")
    private User user;

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
