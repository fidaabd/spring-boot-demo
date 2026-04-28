package com.fst.cabinet.controller;

import com.fst.cabinet.service.PatientService;
import com.fst.cabinet.service.RendezVousService;
import com.fst.cabinet.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final PatientService patientService;
    private final MedecinService medecinService;
    private final RendezVousService rendezVousService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {

        // Dashboard ADMIN
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("totalPatients", patientService.findAll().size());
            model.addAttribute("totalMedecins", medecinService.findAll().size());
            model.addAttribute("rdvAujourdhui", rendezVousService.findAujourdhui());
            model.addAttribute("rdvSemaine", rendezVousService.findSemaine());
            return "dashboard/admin";
        }

        // Dashboard MEDECIN
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MEDECIN"))) {
            model.addAttribute("rdvAujourdhui", rendezVousService.findAujourdhui());
            model.addAttribute("rdvSemaine", rendezVousService.findSemaine());
            return "dashboard/medecin";
        }

        // Dashboard SECRETAIRE
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECRETAIRE"))) {
            model.addAttribute("totalPatients", patientService.findAll().size());
            model.addAttribute("rdvAujourdhui", rendezVousService.findAujourdhui());
            return "dashboard/secretaire";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
}