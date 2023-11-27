package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipment;

    @Column
    private String nameEquip;

    private String description;

    @Column
    private double costArend;

    @Column
    private double deposit;

    @ManyToOne
    @JoinColumn(name = "idTypeEquipment")
    private TypeEquipment typeEquipment;

    @ManyToOne
    @JoinColumn(name = "idViewEquipment")
    private ViewEquipment viewEquipment;

    @Column(nullable = false)
    private String numberEquipment;

}
