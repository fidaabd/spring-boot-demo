package com.fst.cabinet.service;

import com.fst.cabinet.entity.Medecin;
import com.fst.cabinet.repository.MedecinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;

    public List<Medecin> findAll() {
        return medecinRepository.findAll();
    }

    public Medecin findById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé !"));
    }

    public Medecin save(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void delete(Long id) {
        medecinRepository.deleteById(id);
    }

    public List<Medecin> findActifs() {
        return medecinRepository.findByActifTrue();
    }
}