package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserArSpIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUserArSpIn;

    @Column
    private String family;

    @Column
    private String nameU;

    private String patronymic;

    @Column
    private String login;

    @Column
    private String password;

    private String contactInformation;

    private String dateBrit;

    @Column
    private String numberTeleph;

    @ManyToOne
    @JoinColumn(name = "idRoleUser")
    private RoleUser roleUser;

    @ManyToOne
    @JoinColumn(name = "idPostUser")
    private PostUser postUser;

}
