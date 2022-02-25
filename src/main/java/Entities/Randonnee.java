package Entities;

import java.util.Date;

import java.util.Objects;

public class Randonnee {
    int idRandonnee;
    String nomRandonnee;
    String paysRandonnee;
    Date dateRandonnee;
    Date created_at;
    Date updated_at;
    String   imageEndroitRandonnee;

    public Randonnee(int idRandonnee, String nomRandonnee, String paysRandonnee, Date dateRandonnee, Date created_at, Date updated_at, String imageEndroitRandonnee) {
        this.idRandonnee = idRandonnee;
        this.nomRandonnee = nomRandonnee;
        this.paysRandonnee = paysRandonnee;
        this.dateRandonnee = dateRandonnee;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.imageEndroitRandonnee = imageEndroitRandonnee;
    }

    public Randonnee(String nomRandonnee, String paysRandonnee, Date dateRandonnee, Date created_at, Date updated_at, String imageEndroitRandonnee) {
        this.nomRandonnee = nomRandonnee;
        this.paysRandonnee = paysRandonnee;
        this.dateRandonnee = dateRandonnee;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.imageEndroitRandonnee = imageEndroitRandonnee;
    }

    public Randonnee() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Randonnee randonnee = (Randonnee) o;
        return idRandonnee == randonnee.idRandonnee && Objects.equals(nomRandonnee, randonnee.nomRandonnee) && Objects.equals(paysRandonnee, randonnee.paysRandonnee) && Objects.equals(dateRandonnee, randonnee.dateRandonnee) && Objects.equals(created_at, randonnee.created_at) && Objects.equals(updated_at, randonnee.updated_at) && Objects.equals(imageEndroitRandonnee, randonnee.imageEndroitRandonnee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRandonnee, nomRandonnee, paysRandonnee, dateRandonnee, created_at, updated_at, imageEndroitRandonnee);
    }

    @Override
    public String toString() {
        return "Randonnee{" +
                "idRandonnee=" + idRandonnee +
                ", nomRandonnee='" + nomRandonnee + '\'' +
                ", paysRandonnee='" + paysRandonnee + '\'' +
                ", dateRandonnee=" + dateRandonnee +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", imageEndroitRandonnee='" + imageEndroitRandonnee + '\'' +
                '}';
    }

    public int getIdRandonnee() {
        return idRandonnee;
    }

    public void setIdRandonnee(int idRandonnee) {
    this.idRandonnee = idRandonnee;
}

    public String getNomRandonnee() {
        return nomRandonnee;
    }

    public void setNomRandonnee(String nomRandonnee) {
        this.nomRandonnee = nomRandonnee;
    }

    public String getPaysRandonnee() {
        return paysRandonnee;
    }

    public void setPaysRandonnee(String paysRandonnee) {
        this.paysRandonnee = paysRandonnee;
    }

    public Date getDateRandonnee() {
        return dateRandonnee;
    }

    public void setDateRandonnee(Date dateRandonnee) {
        this.dateRandonnee = dateRandonnee;
    }
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getImageEndroitRandonnee() {
        return imageEndroitRandonnee;
    }

    public void setImageEndroitRandonnee(String imageEndroitRandonnee) {
        this.imageEndroitRandonnee = imageEndroitRandonnee;
    }
}