package com.project.management.domain;

import javax.persistence.*;

@Table(name = "skills")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "language")
    private String language;
    @Column(name = "level")
    private int level;

    public Skill() {
    }
}
