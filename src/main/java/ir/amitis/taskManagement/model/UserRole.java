package ir.amitis.taskManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    private LocalDateTime creatAt;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;


    @Override
    public boolean equals(Object object){
        if (object==null ||this.getClass() != object.getClass()){
            return false;
        }
        if (this.getId() != null){
            UserRole userRole= (UserRole) object;
            return this.getId().equals(userRole.getId());
        }else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", creatAt=" + creatAt +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
