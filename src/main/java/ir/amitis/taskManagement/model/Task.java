package ir.amitis.taskManagement.model;

import ir.amitis.taskManagement.dto.TaskSaveDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private LocalDateTime done;
    @Column(nullable = false)
    private String subject;
    @Enumerated(EnumType.STRING)
    private Priority taskPriority;
    @Column(columnDefinition="text")
    private String description;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<TaskType> taskTypes;

    @ManyToOne(optional = false ,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    public static Task taskFromDto(TaskSaveDto taskDto){
        Task task=new Task();
        task.setName(taskDto.name());
        task.setSubject(task.subject);
        task.setTaskPriority(taskDto.priority());
        task.setDescription(taskDto.description());
        task.setTaskTypes(taskDto.types());
        task.setCreateAt(LocalDateTime.now());
        return task;
    };


    @Override
    public boolean equals(Object object){
        if (object==null ||this.getClass() != object.getClass()){
            return false;
        }
        if (this.getId() != null){
            Task task= (Task) object;
            return this.getId().equals(task.getId());
        }else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                ", done=" + done +
                ", subject='" + subject + '\'' +
                ", taskPriority=" + taskPriority +
                ", description='" + description + '\'' +
                ", taskTypes=" + taskTypes +
                ", user=" + user +
                '}';
    }
}
