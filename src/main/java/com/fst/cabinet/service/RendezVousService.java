package com.fst.cabinet.service;

import com.fst.cabinet.entity.RendezVous;
import com.fst.cabinet.entity.StatutRdv;
import com.fst.cabinet.repository.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    public RendezVous findById(Long id) {
        return rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RDV non trouvé !"));
    }

    public RendezVous save(RendezVous rdv) {
        // Vérification chevauchement
        LocalDateTime debut = rdv.getDateHeure();
        LocalDateTime fin = debut.plusMinutes(rdv.getDureeMinutes());

        List<RendezVous> chevauchements = rendezVousRepository.findChevauchements(
                rdv.getMedecin().getId(), debut, fin
        );

        // Si on modifie un RDV existant, on exclut le RDV lui-même
        if (rdv.getId() != null) {
            chevauchements.removeIf(r -> r.getId().equals(rdv.getId()));
        }

        if (!chevauchements.isEmpty()) {
            throw new RuntimeException(
                    "Ce médecin a déjà un rendez-vous sur ce créneau !"
            );
        }

        return rendezVousRepository.save(rdv);
    }

    public void annuler(Long id) {
        RendezVous rdv = findById(id);
        rdv.setStatut(StatutRdv.ANNULE);
        rendezVousRepository.save(rdv);
    }

    public List<RendezVous> findByMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId);
    }

    public List<RendezVous> findByPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId);
    }

    public List<RendezVous> findAujourdhui() {
        LocalDateTime debut = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime fin = debut.plusDays(1);
        return rendezVousRepository.findByDateHeureBetween(debut, fin);
    }

    public List<RendezVous> findSemaine() {
        LocalDateTime debut = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime fin = debut.plusDays(7);
        return rendezVousRepository.findByDateHeureBetween(debut, fin);
    }
}