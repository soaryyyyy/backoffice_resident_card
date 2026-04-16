package itu.trinome.backoffice_resident_card.repository;

import itu.trinome.backoffice_resident_card.model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Integer> {

    List<Piece> findByCommuneTrue();

    List<Piece> findByTypePieceId(Integer idTypePiece);

    @Query("SELECT p FROM Piece p JOIN PieceTypeVisa ptv ON ptv.piece = p WHERE ptv.typeVisa.id = :idTypeVisa")
    List<Piece> findByTypeVisaId(@Param("idTypeVisa") Integer idTypeVisa);
}
