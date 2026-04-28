package com.fst.cabinet.repository;

import com.fst.cabinet.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByMedecinId(Long medecinId);
}

