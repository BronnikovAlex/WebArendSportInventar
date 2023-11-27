package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRefund;

    @Column(nullable = false)
    private String dateRefund;

    @ManyToOne
    @JoinColumn(name = "idDescriptionRefund")
    private DescriptionRefund descriptionRefund;

    @ManyToOne
    @JoinColumn(name = "idConditionRefund")
    private ConditionRefund conditionRefund;

    @ManyToOne
    @JoinColumn(name = "idUserArSpIn")
    private UserArSpIn userArSpIn;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderArend orderArend;

}
