package com.arendinventar.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoleUser;

    @Column(nullable = true)
    private String nameRoleUser;

}
