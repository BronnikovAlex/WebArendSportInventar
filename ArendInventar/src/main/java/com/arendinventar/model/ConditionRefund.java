package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConditionRefund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConditionRefund;

    private String nameCondition;

}
