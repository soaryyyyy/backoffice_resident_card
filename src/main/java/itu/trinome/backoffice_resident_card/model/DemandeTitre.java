package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "demande_titre")
public class DemandeTitre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "id_passeport", nullable = false)
    private Passeport passeport;

    @ManyToOne
    @JoinColumn(name = "id_visa", nullable = false)
    private Visa visa;

    @ManyToOne
    @JoinColumn(name = "id_type_visa", nullable = false)
    private TypeVisa typeVisa;

    @ManyToOne
    @JoinColumn(name = "id_categorie_demande", nullable = false)
    private CategorieDemande categorieDemande;

    @Column(length = 20)
    private String status = "EN_COURS";

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        if (dateCreation == null) {
            dateCreation = LocalDateTime.now();
        }
    }

    public DemandeTitre() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }

    public Passeport getPasseport() { return passeport; }
    public void setPasseport(Passeport passeport) { this.passeport = passeport; }

    public Visa getVisa() { return visa; }
    public void setVisa(Visa visa) { this.visa = visa; }

    public TypeVisa getTypeVisa() { return typeVisa; }
    public void setTypeVisa(TypeVisa typeVisa) { this.typeVisa = typeVisa; }

    public CategorieDemande getCategorieDemande() { return categorieDemande; }
    public void setCategorieDemande(CategorieDemande categorieDemande) { this.categorieDemande = categorieDemande; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}
