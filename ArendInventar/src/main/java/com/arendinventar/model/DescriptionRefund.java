package com.arendinventar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DescriptionRefund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescriptionRefund;

    private String nameDescript;

}
