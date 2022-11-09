package com.example.webtasks.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    private Long id;

    @Getter
    @Setter
    @OneToOne
    private User author;

    @Getter
    @Setter
    private String task;
}
