package ir.amitis.taskManagement.model;
import ir.amitis.taskManagement.dto.UserPostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "app_user")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false,unique = true,columnDefinition = "varchar(30)")
    private String username;

    @Column(nullable = false,columnDefinition = "varchar(255)")
    private String password;

    @OneToMany( mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<UserRole>userRoles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> tasks;
    private boolean deleted;

    public static User userFromDto(UserPostDto userDto){
        User user=new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        return user;
    }



    @Override
    public boolean equals(Object object){
        if (object==null ||this.getClass() != object.getClass()){
            return false;
        }
        if (this.getId() != null){
            User user= (User) object;
            return this.getId().equals(user.getId());
        }else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", profile=" + profile +
                ", tasks=" + tasks +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new HashSet<GrantedAuthority>();
        userRoles.forEach(ur -> authorities.add(new SimpleGrantedAuthority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {return !deleted;
    }

    @Override
    public boolean isAccountNonLocked() {return !deleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {return !deleted;
    }

    @Override
    public boolean isEnabled() {return !deleted;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
