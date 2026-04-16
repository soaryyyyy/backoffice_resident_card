package itu.trinome.backoffice_resident_card.dto;

public class PieceDemandeDTO {

    private Integer idPiece;
    private Boolean fourni;

    public PieceDemandeDTO() {}

    public Integer getIdPiece() { return idPiece; }
    public void setIdPiece(Integer idPiece) { this.idPiece = idPiece; }

    public Boolean getFourni() { return fourni; }
    public void setFourni(Boolean fourni) { this.fourni = fourni; }
}
