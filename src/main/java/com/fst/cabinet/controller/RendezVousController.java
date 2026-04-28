package com.fst.cabinet.controller;

import com.fst.cabinet.entity.RendezVous;
import com.fst.cabinet.service.MedecinService;
import com.fst.cabinet.service.PatientService;
import com.fst.cabinet.service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rdv")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;
    private final MedecinService medecinService;
    private final PatientService patientService;

    // Liste tous les RDV
    @GetMapping
    public String liste(Model model) {
        model.addAttribute("rdvList", rendezVousService.findAll());
        return "rdv/liste";
    }

    // Formulaire nouveau RDV
    @GetMapping("/nouveau")
    public String nouveauForm(Model model) {
        model.addAttribute("rdv", new RendezVous());
        model.addAttribute("medecins", medecinService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "rdv/formulaire";
    }

    // Sauvegarder nouveau RDV
    @PostMapping("/nouveau")
    public String sauvegarder(@ModelAttribute RendezVous rdv) {
        rendezVousService.save(rdv);
        return "redirect:/rdv";
    }

    // Formulaire modifier RDV
    @GetMapping("/modifier/{id}")
    public String modifierForm(@PathVariable Long id, Model model) {
        model.addAttribute("rdv", rendezVousService.findById(id));
        model.addAttribute("medecins", medecinService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "rdv/formulaire";
    }

    // Sauvegarder modification
    @PostMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, @ModelAttribute RendezVous rdv) {
        rdv.setId(id);
        rendezVousService.save(rdv);
        return "redirect:/rdv";
    }

    // Annuler RDV
    @GetMapping("/annuler/{id}")
    public String annuler(@PathVariable Long id) {
        rendezVousService.annuler(id);
        return "redirect:/rdv";
    }
}