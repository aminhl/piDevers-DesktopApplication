package Entities;

import java.util.Objects;

public class Categorie {

    private int idCategorie;
    private String nomCategorie;

    public Categorie(){}
    public Categorie(int idCategorie, String nomCategorie){
        this.idCategorie=idCategorie;
        this.nomCategorie=nomCategorie;
    }
    public Categorie(String nomCategorie){
        this.nomCategorie=nomCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return idCategorie == categorie.idCategorie && nomCategorie.equals(categorie.nomCategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategorie, nomCategorie);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nomCategorie='" + nomCategorie + '\'' +
                '}';
    }
}

