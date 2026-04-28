package com.fst.cabinet.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poste;
    private String etablissement;
    private int anneeDebut;
    private int anneeFin;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
}