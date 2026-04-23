package com.fst.cabinet.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ordonnance")
@Data // lombok data genere automatiquement getters w setters w constructeurr
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ordonnance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //une ordonnance liee a un seul rdv
    @JoinColumn(name = "rendez_vous_id", nullable = false)
    private RendezVous rendezVous;

    @Column(name = "date_emission", nullable = false)
    private LocalDate dateEmission;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL)
    private List<LigneMedicament> medicaments;
}