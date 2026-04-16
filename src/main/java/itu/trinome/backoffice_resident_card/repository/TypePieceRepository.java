package itu.trinome.backoffice_resident_card.repository;

import itu.trinome.backoffice_resident_card.model.TypePiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePieceRepository extends JpaRepository<TypePiece, Integer> {
}
