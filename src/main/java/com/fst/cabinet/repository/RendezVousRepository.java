package com.fst.cabinet.repository;

import com.fst.cabinet.entity.RendezVous;
import com.fst.cabinet.entity.StatutRdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByMedecinId(Long medecinId);

    List<RendezVous> findByPatientId(Long patientId);

    List<RendezVous> findByStatut(StatutRdv statut);

    // RDV du jour
    @Query("SELECT r FROM RendezVous r WHERE r.dateHeure BETWEEN :debut AND :fin")
    List<RendezVous> findByDateHeureBetween(
            @Param("debut") LocalDateTime debut,
            @Param("fin") LocalDateTime fin
    );

    // Vérification chevauchement — version simplifiée compatible Hibernate 6
    @Query("SELECT r FROM RendezVous r WHERE r.medecin.id = :medecinId " +
            "AND r.statut <> com.fst.cabinet.entity.StatutRdv.ANNULE " +
            "AND r.dateHeure < :fin " +
            "AND r.dateHeure > :debut")
    List<RendezVous> findChevauchements(
            @Param("medecinId") Long medecinId,
            @Param("debut") LocalDateTime debut,
            @Param("fin") LocalDateTime fin
    );
}