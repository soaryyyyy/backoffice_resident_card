package itu.trinome.backoffice_resident_card.controller;

import itu.trinome.backoffice_resident_card.dto.DemandeRequestDTO;
import itu.trinome.backoffice_resident_card.service.DemandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demandes")
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping
    public ResponseEntity<?> creerDemande(@RequestBody DemandeRequestDTO dto) {
        try {
            Map<String, Object> result = demandeService.creerDemande(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erreur", e.getMessage()));
        }
    }
}
