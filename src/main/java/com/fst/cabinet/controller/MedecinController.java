package com.fst.cabinet.controller;

import com.fst.cabinet.entity.Medecin;
import com.fst.cabinet.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medecins")
@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;

    // Liste médecins
    @GetMapping
    public String liste(Model model) {
        model.addAttribute("medecins", medecinService.findAll());
        return "medecins/liste";
    }

    // Formulaire ajout
    @GetMapping("/nouveau")
    public String nouveauForm(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecins/formulaire";
    }

    // Sauvegarder ajout
    @PostMapping("/nouveau")
    public String sauvegarder(@ModelAttribute Medecin medecin) {
        medecinService.save(medecin);
        return "redirect:/medecins";
    }

    // Formulaire modification
    @GetMapping("/modifier/{id}")
    public String modifierForm(@PathVariable Long id, Model model) {
        model.addAttribute("medecin", medecinService.findById(id));
        return "medecins/formulaire";
    }

    // Sauvegarder modification
    @PostMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, @ModelAttribute Medecin medecin) {
        medecin.setId(id);
        medecinService.save(medecin);
        return "redirect:/medecins";
    }

    // Supprimer
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        medecinService.delete(id);
        return "redirect:/medecins";
    }

    // Fiche médecin
    @GetMapping("/{id}")
    public String fiche(@PathVariable Long id, Model model) {
        model.addAttribute("medecin", medecinService.findById(id));
        return "medecins/fiche";
    }
}