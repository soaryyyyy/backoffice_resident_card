package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "demande_piece")
public class DemandePiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_demande_titre", nullable = false)
    private DemandeTitre demandeTitre;

    @ManyToOne
    @JoinColumn(name = "id_piece", nullable = false)
    private Piece piece;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean fourni = false;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean valide = false;

    @Column(name = "date_depot")
    private LocalDate dateDepot;

    public DemandePiece() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public DemandeTitre getDemandeTitre() { return demandeTitre; }
    public void setDemandeTitre(DemandeTitre demandeTitre) { this.demandeTitre = demandeTitre; }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }

    public Boolean getFourni() { return fourni; }
    public void setFourni(Boolean fourni) { this.fourni = fourni; }

    public Boolean getValide() { return valide; }
    public void setValide(Boolean valide) { this.valide = valide; }

    public LocalDate getDateDepot() { return dateDepot; }
    public void setDateDepot(LocalDate dateDepot) { this.dateDepot = dateDepot; }
}
