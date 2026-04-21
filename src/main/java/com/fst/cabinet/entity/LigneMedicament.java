package com.fst.cabinet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ligne_medicament")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneMedicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordonnance_id", nullable = false)
    private Ordonnance ordonnance;

    @Column(name = "nom_medicament", nullable = false, length = 200)
    private String nomMedicament;

    @Column(name = "posologie", length = 200)
    private String posologie;

    @Column(name = "duree", length = 100)
    private String duree;
}