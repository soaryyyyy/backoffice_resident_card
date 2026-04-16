package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(name = "nom_jeune_fille", length = 100)
    private String nomJeuneFille;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "id_situation_familiale")
    private SituationFamiliale situationFamiliale;

    @ManyToOne
    @JoinColumn(name = "id_nationalite")
    private Nationalite nationalite;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(length = 100)
    private String profession;

    @Column(length = 150)
    private String email;

    @Column(length = 30)
    private String telephone;

    public Personne() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNomJeuneFille() { return nomJeuneFille; }
    public void setNomJeuneFille(String nomJeuneFille) { this.nomJeuneFille = nomJeuneFille; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public SituationFamiliale getSituationFamiliale() { return situationFamiliale; }
    public void setSituationFamiliale(SituationFamiliale situationFamiliale) { this.situationFamiliale = situationFamiliale; }

    public Nationalite getNationalite() { return nationalite; }
    public void setNationalite(Nationalite nationalite) { this.nationalite = nationalite; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
