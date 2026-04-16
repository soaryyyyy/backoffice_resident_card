package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

@Entity
@Table(name = "piece")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "id_type_piece")
    private TypePiece typePiece;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean commune = false;

    public Piece() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public TypePiece getTypePiece() { return typePiece; }
    public void setTypePiece(TypePiece typePiece) { this.typePiece = typePiece; }

    public Boolean getCommune() { return commune; }
    public void setCommune(Boolean commune) { this.commune = commune; }
}
