package com.fst.cabinet.controller;

import com.fst.cabinet.dto.RdvPublicDto;
import com.fst.cabinet.entity.Patient;
import com.fst.cabinet.entity.RendezVous;
import com.fst.cabinet.entity.StatutRdv;
import com.fst.cabinet.repository.MedecinRepository;
import com.fst.cabinet.repository.PatientRepository;
import com.fst.cabinet.repository.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final RendezVousRepository rendezVousRepository;

    @GetMapping("/accueil")
    public String accueil() {
        return "public/accueil";
    }

    @GetMapping("/profil")
    public String profil() {
        return "public/profil";
    }

    @GetMapping("/rdv")
    public String rdvForm(Model model) {
        model.addAttribute("rdvDto", new RdvPublicDto());
        return "public/rdv";
    }

    @PostMapping("/rdv")
    public String soumettreRdv(@ModelAttribute RdvPublicDto dto,
                               Model model) {
        try {
            // 1. Chercher si patient existe déjà par téléphone
            Patient patient = patientRepository
                    .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
                            dto.getNom(), dto.getPrenom())
                    .stream()
                    .findFirst()
                    .orElse(null);

            // 2. Si patient n'existe pas → créer nouveau
            if (patient == null) {
                patient = new Patient();
                patient.setNom(dto.getNom());
                patient.setPrenom(dto.getPrenom());
                patient.setTelephone(dto.getTelephone());
                patient.setEmail(dto.getEmail());
                patient.setCin("TEMP-" + System.currentTimeMillis());
                patient.setDateNaissance(java.time.LocalDate.of(2000, 1, 1));
                patient = patientRepository.save(patient);
            }

            // 3. Créer le RDV
            RendezVous rdv = new RendezVous();
            rdv.setPatient(patient);
            rdv.setMedecin(medecinRepository.findAll().get(0));
            rdv.setMotif(dto.getMotif());
            rdv.setStatut(StatutRdv.EN_ATTENTE);
            rdv.setDureeMinutes(30);

            // Parser la date
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd'T'HH:mm");
            rdv.setDateHeure(LocalDateTime.parse(dto.getDateHeure(), formatter));

            rendezVousRepository.save(rdv);

            return "redirect:/public/rdv-confirme";

        } catch (Exception e) {
            model.addAttribute("erreur", "Erreur lors de la prise de RDV !");
            return "public/rdv";
        }
    }

    @GetMapping("/rdv-confirme")
    public String rdvConfirme() {
        return "public/rdv-confirme";
    }
}