package ir.amitis.taskManagement.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String main;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<UserRole> userRole;


    @Override
    public boolean equals(Object object){
        if (object==null ||this.getClass() != object.getClass()){
            return false;
        }
        if (this.getId() != null){
            Role role= (Role) object;
            return this.getId().equals(role.getId());
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", main='" + main + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
