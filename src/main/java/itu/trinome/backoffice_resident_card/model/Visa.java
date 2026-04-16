package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visa")
public class Visa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String reference;

    @Column(name = "date_entree", nullable = false)
    private LocalDate dateEntree;

    @ManyToOne
    @JoinColumn(name = "id_lieu_entree")
    private Lieu lieuEntree;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne personne;

    public Visa() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public LocalDate getDateEntree() { return dateEntree; }
    public void setDateEntree(LocalDate dateEntree) { this.dateEntree = dateEntree; }

    public Lieu getLieuEntree() { return lieuEntree; }
    public void setLieuEntree(Lieu lieuEntree) { this.lieuEntree = lieuEntree; }

    public LocalDate getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }

    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }
}
