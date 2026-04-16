package itu.trinome.backoffice_resident_card.controller;

import itu.trinome.backoffice_resident_card.model.Piece;
import itu.trinome.backoffice_resident_card.service.PieceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pieces")
public class PieceController {

    private final PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    // GET /pieces?type=commune
    // GET /pieces?typePiece={idTypePiece}
    // GET /pieces?typeVisa={idTypeVisa}
    @GetMapping
    public ResponseEntity<List<Piece>> getPieces(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer typePiece,
            @RequestParam(required = false) Integer typeVisa
    ) {
        if ("commune".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(pieceService.getAllPieceCommune());
        }
        if (typePiece != null) {
            return ResponseEntity.ok(pieceService.getPieceByType(typePiece));
        }
        if (typeVisa != null) {
            return ResponseEntity.ok(pieceService.getPieceByTypeVisa(typeVisa));
        }
        return ResponseEntity.badRequest().build();
    }
}
