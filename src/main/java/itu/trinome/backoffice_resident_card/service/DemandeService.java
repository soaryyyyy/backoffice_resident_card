package itu.trinome.backoffice_resident_card.service;

import itu.trinome.backoffice_resident_card.dto.DemandeRequestDTO;
import itu.trinome.backoffice_resident_card.dto.PieceDemandeDTO;
import itu.trinome.backoffice_resident_card.model.*;
import itu.trinome.backoffice_resident_card.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class DemandeService {

    private final PersonneRepository personneRepository;
    private final PasseportRepository passeportRepository;
    private final VisaRepository visaRepository;
    private final DemandeTitreRepository demandeTitreRepository;
    private final DemandePieceRepository demandePieceRepository;
    private final PieceRepository pieceRepository;
    private final TypeVisaRepository typeVisaRepository;
    private final CategorieDemandeRepository categorieDemandeRepository;
    private final SituationFamilialeRepository situationFamilialeRepository;
    private final NationaliteRepository nationaliteRepository;
    private final LieuRepository lieuRepository;

    public DemandeService(
            PersonneRepository personneRepository,
            PasseportRepository passeportRepository,
            VisaRepository visaRepository,
            DemandeTitreRepository demandeTitreRepository,
            DemandePieceRepository demandePieceRepository,
            PieceRepository pieceRepository,
            TypeVisaRepository typeVisaRepository,
            CategorieDemandeRepository categorieDemandeRepository,
            SituationFamilialeRepository situationFamilialeRepository,
            NationaliteRepository nationaliteRepository,
            LieuRepository lieuRepository
    ) {
        this.personneRepository = personneRepository;
        this.passeportRepository = passeportRepository;
        this.visaRepository = visaRepository;
        this.demandeTitreRepository = demandeTitreRepository;
        this.demandePieceRepository = demandePieceRepository;
        this.pieceRepository = pieceRepository;
        this.typeVisaRepository = typeVisaRepository;
        this.categorieDemandeRepository = categorieDemandeRepository;
        this.situationFamilialeRepository = situationFamilialeRepository;
        this.nationaliteRepository = nationaliteRepository;
        this.lieuRepository = lieuRepository;
    }

    @Transactional
    public Map<String, Object> creerDemande(DemandeRequestDTO dto) {
        valider(dto);

        Personne personne = trouverOuCreerPersonne(dto);
        Passeport passeport = trouverOuCreerPasseport(dto, personne);
        Visa visa = creerVisa(dto, personne);

        TypeVisa typeVisa = typeVisaRepository.findById(dto.getIdTypeVisa())
                .orElseThrow(() -> new RuntimeException("Type de visa introuvable"));

        CategorieDemande categorie = categorieDemandeRepository.findByLibelle("NOUVEAU_TITRE")
                .orElseThrow(() -> new RuntimeException("Catégorie NOUVEAU_TITRE introuvable en base"));

        DemandeTitre demande = new DemandeTitre();
        demande.setPersonne(personne);
        demande.setPasseport(passeport);
        demande.setVisa(visa);
        demande.setTypeVisa(typeVisa);
        demande.setCategorieDemande(categorie);
        demande.setStatus("EN_COURS");
        demande = demandeTitreRepository.save(demande);

        creerDemandePieces(demande, dto);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", demande.getId());
        response.put("status", demande.getStatus());
        response.put("dateCreation", demande.getDateCreation());
        return response;
    }

    // -------------------------------------------------------------------------
    // Validation préalable
    // -------------------------------------------------------------------------

    private void valider(DemandeRequestDTO dto) {
        // Champs obligatoires
        if (blank(dto.getNom()))              throw new RuntimeException("Le nom est obligatoire");
        if (blank(dto.getPrenom()))           throw new RuntimeException("Le prénom est obligatoire");
        if (dto.getDateNaissance() == null)   throw new RuntimeException("La date de naissance est obligatoire");
        if (dto.getIdSituationFamiliale() == null) throw new RuntimeException("La situation familiale est obligatoire");
        if (dto.getIdNationalite() == null)   throw new RuntimeException("La nationalité est obligatoire");
        if (blank(dto.getNumeroPasseport()))  throw new RuntimeException("Le numéro de passeport est obligatoire");
        if (dto.getDateDelivrancePasseport() == null) throw new RuntimeException("La date de délivrance du passeport est obligatoire");
        if (dto.getDateExpirationPasseport() == null) throw new RuntimeException("La date d'expiration du passeport est obligatoire");
        if (blank(dto.getReferenceVisa()))    throw new RuntimeException("La référence du visa est obligatoire");
        if (dto.getDateEntreeVisa() == null)  throw new RuntimeException("La date d'entrée du visa est obligatoire");
        if (dto.getDateExpirationVisa() == null) throw new RuntimeException("La date d'expiration du visa est obligatoire");
        if (dto.getIdTypeVisa() == null)      throw new RuntimeException("Le type de visa demandé est obligatoire");

        // Passeport non expiré
        if (dto.getDateExpirationPasseport().isBefore(LocalDate.now()))
            throw new RuntimeException("Le passeport est expiré");

        // Visa non expiré
        if (dto.getDateExpirationVisa().isBefore(LocalDate.now()))
            throw new RuntimeException("Le visa est expiré");

        // Cohérence des dates passeport
        if (dto.getDateDelivrancePasseport().isAfter(dto.getDateExpirationPasseport()))
            throw new RuntimeException("La date de délivrance du passeport est postérieure à sa date d'expiration");
    }

    // -------------------------------------------------------------------------
    // Logique métier
    // -------------------------------------------------------------------------

    private Personne trouverOuCreerPersonne(DemandeRequestDTO dto) {
        Optional<Personne> existing = personneRepository
                .findByNomAndPrenomAndDateNaissance(dto.getNom(), dto.getPrenom(), dto.getDateNaissance());
        if (existing.isPresent()) return existing.get();

        SituationFamiliale sf = situationFamilialeRepository.findById(dto.getIdSituationFamiliale())
                .orElseThrow(() -> new RuntimeException("Situation familiale introuvable"));
        Nationalite nat = nationaliteRepository.findById(dto.getIdNationalite())
                .orElseThrow(() -> new RuntimeException("Nationalité introuvable"));

        Personne p = new Personne();
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setNomJeuneFille(dto.getNomJeuneFille());
        p.setDateNaissance(dto.getDateNaissance());
        p.setSituationFamiliale(sf);
        p.setNationalite(nat);
        p.setAdresse(dto.getAdresse());
        p.setProfession(dto.getProfession());
        p.setEmail(dto.getEmail());
        p.setTelephone(dto.getTelephone());
        return personneRepository.save(p);
    }

    private Passeport trouverOuCreerPasseport(DemandeRequestDTO dto, Personne personne) {
        Optional<Passeport> existing = passeportRepository.findByNumero(dto.getNumeroPasseport());
        if (existing.isPresent()) {
            Passeport p = existing.get();
            if (!p.getPersonne().getId().equals(personne.getId()))
                throw new RuntimeException("Ce numéro de passeport appartient à une autre personne");
            return p;
        }

        Passeport p = new Passeport();
        p.setNumero(dto.getNumeroPasseport());
        p.setDateDelivrance(dto.getDateDelivrancePasseport());
        p.setDateExpiration(dto.getDateExpirationPasseport());
        p.setPersonne(personne);
        return passeportRepository.save(p);
    }

    private Visa creerVisa(DemandeRequestDTO dto, Personne personne) {
        Visa v = new Visa();
        v.setReference(dto.getReferenceVisa());
        v.setDateEntree(dto.getDateEntreeVisa());
        v.setDateExpiration(dto.getDateExpirationVisa());
        v.setPersonne(personne);
        if (dto.getIdLieuEntree() != null) {
            Lieu lieu = lieuRepository.findById(dto.getIdLieuEntree())
                    .orElseThrow(() -> new RuntimeException("Lieu d'entrée introuvable"));
            v.setLieuEntree(lieu);
        }
        return visaRepository.save(v);
    }

    private void creerDemandePieces(DemandeTitre demande, DemandeRequestDTO dto) {
        // Pièces communes + pièces spécifiques au type visa (dédupliquées)
        List<Piece> communes = pieceRepository.findByCommuneTrue();
        List<Piece> specifiques = pieceRepository.findByTypeVisaId(dto.getIdTypeVisa());

        Set<Integer> seen = new HashSet<>();
        List<Piece> toutes = new ArrayList<>();
        for (Piece p : communes) {
            if (seen.add(p.getId())) toutes.add(p);
        }
        for (Piece p : specifiques) {
            if (seen.add(p.getId())) toutes.add(p);
        }

        // Fourni envoyé par le frontend
        Map<Integer, Boolean> fourniMap = new HashMap<>();
        if (dto.getPieces() != null) {
            for (PieceDemandeDTO pd : dto.getPieces()) {
                fourniMap.put(pd.getIdPiece(), Boolean.TRUE.equals(pd.getFourni()));
            }
        }

        for (Piece piece : toutes) {
            DemandePiece dp = new DemandePiece();
            dp.setDemandeTitre(demande);
            dp.setPiece(piece);
            dp.setFourni(fourniMap.getOrDefault(piece.getId(), false));
            dp.setValide(false);
            dp.setDateDepot(null);
            demandePieceRepository.save(dp);
        }
    }

    private boolean blank(String s) {
        return s == null || s.isBlank();
    }
}
