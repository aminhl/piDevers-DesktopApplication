package Entities;

import java.util.Date;

public class Commentaire {
    private int idCommentaire;
    private String auteurCommentaire;
    private String contenuCommentaire;
    private String dateCommentaire;
    private String etatCommentaire;
    private int idArticle;
    private int idUtilisateur;

    public Commentaire(){
    }
    /*Constructor with ID */
    public Commentaire(int idCommentaire, String auteurCommentaire, String contenuCommentaire, String dateCommentaire, String etatCommentaire, int idArticle,int idUtilisateur) {
        this.idCommentaire = idCommentaire;
        this.auteurCommentaire = auteurCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idArticle=idArticle;
        this.idUtilisateur=idUtilisateur;
    }

    /*Constructor with No ID */
    public Commentaire(String auteurCommentaire, String contenuCommentaire, String dateCommentaire, String etatCommentaire, int idArticle,int idUtilisateur) {
        this.auteurCommentaire = auteurCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idArticle=idArticle;
        this.idUtilisateur=idUtilisateur;


    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getAuteurCommentaire() {
        return auteurCommentaire;
    }

    public void setAuteurCommentaire(String auteurCommentaire) {
        this.auteurCommentaire = auteurCommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public String getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(String dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public String getEtatCommentaire() {
        return etatCommentaire;
    }

    public void setEtatCommentaire(String etatCommentaire) {
        this.etatCommentaire = etatCommentaire;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "idCommentaire=" + idCommentaire +
                ", auteurCommentaire='" + auteurCommentaire + '\'' +
                ", contenuCommentaire='" + contenuCommentaire + '\'' +
                ", dateCommentaire='" + dateCommentaire + '\'' +
                ", etatCommentaire='" + etatCommentaire + '\'' +
                ", idArticle=" + idArticle +
                ", idUtilisateur=" + idUtilisateur +
                '}';
    }
}
