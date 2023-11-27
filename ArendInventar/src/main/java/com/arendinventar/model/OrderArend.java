package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderArend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private String statusOrder;

    @Column(nullable = false)
    private String dateNachaloArend;

    @Column(nullable = false)
    private String dateEndArend;

    @Column(nullable = false)
    private String term;

    @ManyToOne
    @JoinColumn(name = "idEquipment")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "idUserArSpIn")
    private UserArSpIn userArSpIn;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private UserArSpIn client;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private UserArSpIn employee;

    @ManyToOne
    @JoinColumn(name = "idRefund")
    private Refund refund;

    @ManyToOne
    @JoinColumn(name = "idConditionOrder")
    private ConditionOrder conditionOrder;
}
