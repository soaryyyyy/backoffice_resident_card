package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

@Entity
@Table(name = "piece_type_visa")
public class PieceTypeVisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_piece", nullable = false)
    private Piece piece;

    @ManyToOne
    @JoinColumn(name = "id_type_visa", nullable = false)
    private TypeVisa typeVisa;

    public PieceTypeVisa() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }

    public TypeVisa getTypeVisa() { return typeVisa; }
    public void setTypeVisa(TypeVisa typeVisa) { this.typeVisa = typeVisa; }
}
