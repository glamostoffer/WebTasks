package com.example.webtasks.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private User author;

    @Getter
    @Setter
    @ManyToOne
    private Tables tables;

    @Getter
    @Setter
    private String task;
}
