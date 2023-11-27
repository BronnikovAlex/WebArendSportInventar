package com.arendinventar.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TypeEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeEquipment;

    @Column(nullable = false)
    private String nameTypeEquipment;

}
