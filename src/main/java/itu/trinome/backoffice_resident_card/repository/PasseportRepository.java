package itu.trinome.backoffice_resident_card.repository;

import itu.trinome.backoffice_resident_card.model.Passeport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasseportRepository extends JpaRepository<Passeport, Integer> {

    Optional<Passeport> findByNumero(String numero);
}
