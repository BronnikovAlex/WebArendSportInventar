package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ConditionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConditionOrder;

    @Column(nullable = false)
    private String nameConditionOrder;

}
