package com.fst.cabinet.controller;

import com.fst.cabinet.service.MedecinService;
import com.fst.cabinet.service.PatientService;
import com.fst.cabinet.service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        model.addAttribute("totalPatients", patientService.findAll().size());
        model.addAttribute("totalMedecins", medecinService.findAll().size());
        model.addAttribute("rdvAujourdhui", rendezVousService.findAujourdhui());
        model.addAttribute("rdvSemaine", rendezVousService.findSemaine());

        // Rediriger selon le rôle
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "dashboard/admin";
        } else if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MEDECIN"))) {
            return "dashboard/medecin";
        } else {
            return "dashboard/secretaire";
        }
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