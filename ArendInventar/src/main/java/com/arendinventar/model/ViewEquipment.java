package com.arendinventar.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ViewEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViewEquipment;

    @Column(nullable = false)
    private String nameViewEquipment;

    @Column(nullable = false)
    private double cost;

}
