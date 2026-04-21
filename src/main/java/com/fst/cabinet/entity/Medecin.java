package com.fst.cabinet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medecin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "specialite", nullable = false, length = 100)
    private String specialite;

    @Column(name = "numero_ordre", unique = true, nullable = false, length = 50)
    private String numeroOrdre;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "actif", nullable = false)
    private Boolean actif = true;
}