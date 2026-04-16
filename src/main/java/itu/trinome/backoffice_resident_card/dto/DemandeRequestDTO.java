package itu.trinome.backoffice_resident_card.dto;

import java.time.LocalDate;
import java.util.List;

public class DemandeRequestDTO {

    // État civil
    private String nom;
    private String prenom;
    private String nomJeuneFille;
    private LocalDate dateNaissance;
    private Integer idSituationFamiliale;
    private Integer idNationalite;
    private String adresse;
    private String profession;
    private String email;
    private String telephone;

    // Passeport
    private String numeroPasseport;
    private LocalDate dateDelivrancePasseport;
    private LocalDate dateExpirationPasseport;

    // Visa transformable
    private String referenceVisa;
    private LocalDate dateEntreeVisa;
    private Integer idLieuEntree;
    private LocalDate dateExpirationVisa;

    // Type de visa demandé
    private Integer idTypeVisa;

    // Pièces justificatives
    private List<PieceDemandeDTO> pieces;

    public DemandeRequestDTO() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNomJeuneFille() { return nomJeuneFille; }
    public void setNomJeuneFille(String nomJeuneFille) { this.nomJeuneFille = nomJeuneFille; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public Integer getIdSituationFamiliale() { return idSituationFamiliale; }
    public void setIdSituationFamiliale(Integer idSituationFamiliale) { this.idSituationFamiliale = idSituationFamiliale; }

    public Integer getIdNationalite() { return idNationalite; }
    public void setIdNationalite(Integer idNationalite) { this.idNationalite = idNationalite; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getNumeroPasseport() { return numeroPasseport; }
    public void setNumeroPasseport(String numeroPasseport) { this.numeroPasseport = numeroPasseport; }

    public LocalDate getDateDelivrancePasseport() { return dateDelivrancePasseport; }
    public void setDateDelivrancePasseport(LocalDate dateDelivrancePasseport) { this.dateDelivrancePasseport = dateDelivrancePasseport; }

    public LocalDate getDateExpirationPasseport() { return dateExpirationPasseport; }
    public void setDateExpirationPasseport(LocalDate dateExpirationPasseport) { this.dateExpirationPasseport = dateExpirationPasseport; }

    public String getReferenceVisa() { return referenceVisa; }
    public void setReferenceVisa(String referenceVisa) { this.referenceVisa = referenceVisa; }

    public LocalDate getDateEntreeVisa() { return dateEntreeVisa; }
    public void setDateEntreeVisa(LocalDate dateEntreeVisa) { this.dateEntreeVisa = dateEntreeVisa; }

    public Integer getIdLieuEntree() { return idLieuEntree; }
    public void setIdLieuEntree(Integer idLieuEntree) { this.idLieuEntree = idLieuEntree; }

    public LocalDate getDateExpirationVisa() { return dateExpirationVisa; }
    public void setDateExpirationVisa(LocalDate dateExpirationVisa) { this.dateExpirationVisa = dateExpirationVisa; }

    public Integer getIdTypeVisa() { return idTypeVisa; }
    public void setIdTypeVisa(Integer idTypeVisa) { this.idTypeVisa = idTypeVisa; }

    public List<PieceDemandeDTO> getPieces() { return pieces; }
    public void setPieces(List<PieceDemandeDTO> pieces) { this.pieces = pieces; }
}
