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
    private final PatientService patientService;
    private final MedecinService medecinService;

    @GetMapping("/liste")
    public String liste(Model model) {
        model.addAttribute("rdvList", rendezVousService.findAll());
        return "rdv/liste";
    }

    @GetMapping("/calendrier")
    public String calendrier(Model model) {
        // Envoyer les RDV de la semaine au calendrier
        model.addAttribute("rdvSemaine", rendezVousService.findSemaine());
        return "rdv/calendrier";
    }

    @GetMapping("/nouveau")
    public String nouveauForm(Model model) {
        model.addAttribute("rendezVous", new RendezVous());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("medecins", medecinService.findAll());
        return "rdv/formulaire";
    }

    @PostMapping("/nouveau")
    public String sauvegarder(@ModelAttribute RendezVous rdv,
                              Model model) {
        try {
            rendezVousService.save(rdv);
            return "redirect:/rdv/liste";
        } catch (RuntimeException e) {
            model.addAttribute("erreur", e.getMessage());
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("medecins", medecinService.findAll());
            return "rdv/formulaire";
        }
    }

    @GetMapping("/modifier/{id}")
    public String modifierForm(@PathVariable Long id, Model model) {
        model.addAttribute("rendezVous", rendezVousService.findById(id));
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("medecins", medecinService.findAll());
        return "rdv/formulaire";
    }

    @PostMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id,
                           @ModelAttribute RendezVous rdv,
                           Model model) {
        try {
            rdv.setId(id);
            rendezVousService.save(rdv);
            return "redirect:/rdv/liste";
        } catch (RuntimeException e) {
            model.addAttribute("erreur", e.getMessage());
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("medecins", medecinService.findAll());
            return "rdv/formulaire";
        }
    }

    @GetMapping("/annuler/{id}")
    public String annuler(@PathVariable Long id) {
        rendezVousService.annuler(id);
        return "redirect:/rdv/liste";
    }
}