package itu.trinome.backoffice_resident_card.controller;

import itu.trinome.backoffice_resident_card.model.SituationFamiliale;
import itu.trinome.backoffice_resident_card.repository.SituationFamilialeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final SituationFamilialeRepository situationFamilialeRepository;

    public TestController(SituationFamilialeRepository situationFamilialeRepository) {
        this.situationFamilialeRepository = situationFamilialeRepository;
    }

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Le serveur fonctionne correctement");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/situations-familiales")
    public ResponseEntity<List<SituationFamiliale>> getSituationsFamiliales() {
        List<SituationFamiliale> list = situationFamilialeRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
