package com.fst.cabinet.repository;

import com.fst.cabinet.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Recherche par nom ou prénom
    List<Patient> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    // Recherche par CIN
    Patient findByCin(String cin);

    // Recherche globale
    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "p.cin LIKE CONCAT('%', :keyword, '%') OR " +
            "p.telephone LIKE CONCAT('%', :keyword, '%')")
    List<Patient> recherche(@Param("keyword") String keyword);
}