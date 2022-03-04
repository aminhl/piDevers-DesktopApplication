package Entities;

import java.sql.Date;

public class Evenement {
    private int idEvenement;
    private String titreEvenement;
    private String lieuEvenement;
    private String typeEvenement;
    private String descriptionEvenement;
    private Date dateEvenement;
    private String imageEvenement;

    public Evenement() {
    }

    public Evenement(String titreEvenement, String lieuEvenement, String typeEvenement, String descriptionEvenement,
                     Date dateEvenement, String imageEvenement) {
        this.titreEvenement = titreEvenement;
        this.lieuEvenement = lieuEvenement;
        this.typeEvenement = typeEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.dateEvenement = dateEvenement;
        this.imageEvenement = imageEvenement;
    }

    public Evenement(int idEvenement, String titreEvenement, String lieuEvenement, String typeEvenement,
                     String descriptionEvenement, Date dateEvenement, String imageEvenement) {
        this.idEvenement = idEvenement;
        this.titreEvenement = titreEvenement;
        this.lieuEvenement = lieuEvenement;
        this.typeEvenement = typeEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.dateEvenement = dateEvenement;
        this.imageEvenement = imageEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getTitreEvenement() {
        return titreEvenement;
    }

    public void setTitreEvenement(String titreEvenement) {
        this.titreEvenement = titreEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getImageEvenement() {
        return imageEvenement;
    }

    public void setImageEvenement(String imageEvenement) {
        this.imageEvenement = imageEvenement;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "idEvenement=" + idEvenement +
                ", titreEvenement='" + titreEvenement + '\'' +
                ", lieuEvenement='" + lieuEvenement + '\'' +
                ", typeEvenement='" + typeEvenement + '\'' +
                ", descriptionEvenement='" + descriptionEvenement + '\'' +
                ", dateEvenement='" + dateEvenement + '\'' +
                ", imageEvenement='" + imageEvenement + '\'' +
                '}';
    }
}
