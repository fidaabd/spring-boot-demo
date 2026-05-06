package com.fst.cabinet.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//un rdv appartient l un seul patient w medecin
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;

    @Column(name = "date_heure", nullable = false)
    private LocalDateTime dateHeure;

    @Column(name = "duree_minutes")
    @Builder.Default
    private int dureeMinutes = 30;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    @Builder.Default
    private StatutRdv statut = StatutRdv.PLANIFIE;

    @Column(name = "motif", columnDefinition = "TEXT")
    private String motif;
}