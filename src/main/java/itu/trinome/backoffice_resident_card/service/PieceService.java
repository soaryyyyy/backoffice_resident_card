package itu.trinome.backoffice_resident_card.service;

import itu.trinome.backoffice_resident_card.model.Piece;
import itu.trinome.backoffice_resident_card.repository.PieceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {

    private final PieceRepository pieceRepository;

    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public List<Piece> getAllPieceCommune() {
        return pieceRepository.findByCommuneTrue();
    }

    public List<Piece> getPieceByType(int idTypePiece) {
        return pieceRepository.findByTypePieceId(idTypePiece);
    }

    public List<Piece> getPieceByTypeVisa(int idTypeVisa) {
        return pieceRepository.findByTypeVisaId(idTypeVisa);
    }
}
