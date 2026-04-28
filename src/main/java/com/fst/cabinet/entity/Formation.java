package com.fst.cabinet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diplome;
    private String etablissement;
    private int annee;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
}

