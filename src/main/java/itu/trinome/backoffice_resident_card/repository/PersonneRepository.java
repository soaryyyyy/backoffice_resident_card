package itu.trinome.backoffice_resident_card.repository;

import itu.trinome.backoffice_resident_card.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByNomAndPrenomAndDateNaissance(String nom, String prenom, LocalDate dateNaissance);
}
