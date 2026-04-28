package com.fst.cabinet.controller;

import com.fst.cabinet.entity.Medecin;
import com.fst.cabinet.entity.RendezVous;
import com.fst.cabinet.repository.ExperienceRepository;
import com.fst.cabinet.repository.FormationRepository;
import com.fst.cabinet.service.MedecinService;
import com.fst.cabinet.service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final MedecinService medecinService;
    private final RendezVousService rendezVousService;
    private final FormationRepository formationRepository;
    private final ExperienceRepository experienceRepository;

    // Liste de tous les médecins
    @GetMapping("/medecins")
    public String listeMedecins(Model model) {
        model.addAttribute("medecins", medecinService.findAll());
        return "public/accueil";
    }

    // Profil d'un médecin avec formations et expériences
    @GetMapping("/medecin/{id}")
    public String profilMedecin(@PathVariable Long id, Model model) {
        Medecin medecin = medecinService.findById(id);
        model.addAttribute("medecin", medecin);
        model.addAttribute("formations", formationRepository.findByMedecinId(id));
        model.addAttribute("experiences", experienceRepository.findByMedecinId(id));
        return "public/profil";
    }

    // Formulaire de RDV en ligne
    @GetMapping("/rdv/{medecinId}")
    public String formulaireRdv(@PathVariable Long medecinId,
                                @RequestParam(required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                Model model) {
        if (date == null) date = LocalDate.now();
        model.addAttribute("medecin", medecinService.findById(medecinId));
        model.addAttribute("creneaux", rendezVousService.getCreneauxDisponibles(medecinId, date));
        model.addAttribute("rdv", new RendezVous());
        model.addAttribute("date", date);
        return "public/rdv";
    }

    // Soumission du formulaire RDV
    @PostMapping("/rdv/{medecinId}")
    public String soumettreRdv(@PathVariable Long medecinId,
                               @ModelAttribute RendezVous rdv) {
        rdv.setMedecin(medecinService.findById(medecinId));
        rendezVousService.prendreRdvEnLigne(rdv);
        return "redirect:/public/medecin/" + medecinId + "?success";
    }
}
