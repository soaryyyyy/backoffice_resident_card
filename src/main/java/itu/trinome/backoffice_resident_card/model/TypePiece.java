package itu.trinome.backoffice_resident_card.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_piece")
public class TypePiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String libelle;

    public TypePiece() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
