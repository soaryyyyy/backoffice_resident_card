package itu.trinome.backoffice_resident_card.service;

import itu.trinome.backoffice_resident_card.model.*;
import itu.trinome.backoffice_resident_card.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceService {

    private final SituationFamilialeRepository situationFamilialeRepository;
    private final NationaliteRepository nationaliteRepository;
    private final LieuRepository lieuRepository;
    private final CategorieDemandeRepository categorieDemandeRepository;
    private final TypeVisaRepository typeVisaRepository;
    private final TypePieceRepository typePieceRepository;

    public ReferenceService(
            SituationFamilialeRepository situationFamilialeRepository,
            NationaliteRepository nationaliteRepository,
            LieuRepository lieuRepository,
            CategorieDemandeRepository categorieDemandeRepository,
            TypeVisaRepository typeVisaRepository,
            TypePieceRepository typePieceRepository
    ) {
        this.situationFamilialeRepository = situationFamilialeRepository;
        this.nationaliteRepository = nationaliteRepository;
        this.lieuRepository = lieuRepository;
        this.categorieDemandeRepository = categorieDemandeRepository;
        this.typeVisaRepository = typeVisaRepository;
        this.typePieceRepository = typePieceRepository;
    }

    public List<SituationFamiliale> getSituationFamiliale() {
        return situationFamilialeRepository.findAll();
    }

    public List<Nationalite> getNationalite() {
        return nationaliteRepository.findAll();
    }

    public List<Lieu> getLieu() {
        return lieuRepository.findAll();
    }

    public List<CategorieDemande> getCategorieDemande() {
        return categorieDemandeRepository.findAll();
    }

    public List<TypeVisa> getTypeVisa() {
        return typeVisaRepository.findAll();
    }

    public List<TypePiece> getTypePieceById(int id) {
        return typePieceRepository.findById(id)
                .map(List::of)
                .orElse(List.of());
    }
}
