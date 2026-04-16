package itu.trinome.backoffice_resident_card.repository;

import itu.trinome.backoffice_resident_card.model.CategorieDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieDemandeRepository extends JpaRepository<CategorieDemande, Integer> {

    Optional<CategorieDemande> findByLibelle(String libelle);
}
