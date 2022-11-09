package com.example.webtasks.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Getter
    @Setter
    @OneToMany
    private List<Task> task;

    @Getter
    @Setter
    private String taskId;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "tables", referencedColumnName = "id")
    @Getter
    @Setter
    private User user;

    @Override
    public boolean equals(Object o) {
        boolean Result = false;
        if (o instanceof Tables) {
            Tables AnotherTask = (Tables) o;
            Result = AnotherTask.taskId.equals(this.taskId);
        }
        return Result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, user);
    }
}
