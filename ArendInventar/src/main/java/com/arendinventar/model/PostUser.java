package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PostUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostUser;

    @Column(nullable = true)
    private String namePost;

    @Column(nullable = true)
    private String authority;

    @Column(nullable = true)
    private double salary;

}
