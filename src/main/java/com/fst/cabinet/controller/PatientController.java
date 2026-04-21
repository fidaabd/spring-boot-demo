package com.fst.cabinet.controller;

import com.fst.cabinet.entity.Patient;
import com.fst.cabinet.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // Liste patients
    @GetMapping
    public String liste(Model model, @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("patients", patientService.recherche(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("patients", patientService.findAll());
        }
        return "patients/liste";
    }

    // Formulaire ajout
    @GetMapping("/nouveau")
    public String nouveauForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/formulaire";
    }

    // Sauvegarder ajout
    @PostMapping("/nouveau")
    public String sauvegarder(@ModelAttribute Patient patient) {
        patientService.save(patient);
        return "redirect:/patients";
    }

    // Formulaire modification
    @GetMapping("/modifier/{id}")
    public String modifierForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findById(id));
        return "patients/formulaire";
    }

    // Sauvegarder modification
    @PostMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, @ModelAttribute Patient patient) {
        patient.setId(id);
        patientService.save(patient);
        return "redirect:/patients";
    }

    // Supprimer
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patients";
    }

    // Fiche patient
    @GetMapping("/{id}")
    public String fiche(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.findById(id));
        return "patients/fiche";
    }
}