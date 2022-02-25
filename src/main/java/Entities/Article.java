package Entities;

import java.util.Date;

public class Article {
    private int idArticle;
    private String titreArticle;
    private String descriptionArticle;
    private String imageArticle;
    private String dateArticle;
    private int idCategorie;
    private int idUtilisateur;


    public Article(){
    }

    /* Constructor with ID*/
    public Article(int idArticle, String titreArticle, String descriptionArticle, String imageArticle, String dateArticle,int idCategorie, int idUtilisateur) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.imageArticle = imageArticle;
        this.idCategorie=idCategorie;
        this.dateArticle = dateArticle;
        this.idUtilisateur=idUtilisateur;

    }
    /* Constructor with NO ID*/
    public Article( String titreArticle, String descriptionArticle, String imageArticle, String dateArticle,int idCategorie,int idUtilisateur) {
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.imageArticle = imageArticle;
        this.idCategorie=idCategorie;
        this.dateArticle = dateArticle;
        this.idUtilisateur=idUtilisateur;

    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public String getImageArticle() {
        return imageArticle;
    }

    public void setImageArticle(String imageArticle) {
        this.imageArticle = imageArticle;
    }

    public int getidCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDateArticle() {
        return dateArticle;
    }

    public void setDateArticle(String dateArticle) {
        this.dateArticle = dateArticle;
    }

    public int getidUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", titreArticle='" + titreArticle + '\'' +
                ", descriptionArticle='" + descriptionArticle + '\'' +
                ", imageArticle='" + imageArticle + '\'' +
                ", dateArticle='" + dateArticle + '\'' +
                ", idCategorie='" + idCategorie + '\'' +
                ", idUtilisateur='" + idUtilisateur + '\'' +
                '}';
    }
}
