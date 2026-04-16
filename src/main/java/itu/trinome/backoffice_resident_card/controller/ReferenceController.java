package itu.trinome.backoffice_resident_card.controller;

import itu.trinome.backoffice_resident_card.model.*;
import itu.trinome.backoffice_resident_card.service.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferenceController {

    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping("/situations-familiales")
    public ResponseEntity<List<SituationFamiliale>> getSituationsFamiliales() {
        return ResponseEntity.ok(referenceService.getSituationFamiliale());
    }

    @GetMapping("/nationalites")
    public ResponseEntity<List<Nationalite>> getNationalites() {
        return ResponseEntity.ok(referenceService.getNationalite());
    }

    @GetMapping("/lieux")
    public ResponseEntity<List<Lieu>> getLieux() {
        return ResponseEntity.ok(referenceService.getLieu());
    }

    @GetMapping("/categories-demandes")
    public ResponseEntity<List<CategorieDemande>> getCategoriesDemandes() {
        return ResponseEntity.ok(referenceService.getCategorieDemande());
    }

    @GetMapping("/types-visa")
    public ResponseEntity<List<TypeVisa>> getTypesVisa() {
        return ResponseEntity.ok(referenceService.getTypeVisa());
    }
}
