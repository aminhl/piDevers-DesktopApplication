package Entities;

public class Commentaire {
    private int idCommentaire;
    private String auteurCommentaire;
    private String contenuCommentaire;
    private String dateCommentaire;
    private int etatCommentaire;
    private Article article;
    private Utilisateur utilisateur;

    private int idArticle;
    private int idUtilisateur;

    public Commentaire(){
    }
    /*Constructor with ID */
    public Commentaire(int idCommentaire,  String contenuCommentaire, String dateCommentaire, int etatCommentaire, int idArticle,int idUtilisateur) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idArticle=idArticle;
        this.idUtilisateur=idUtilisateur;
    }

    /*Constructor with No ID */
    public Commentaire( String contenuCommentaire, String dateCommentaire, int etatCommentaire, int idArticle,int idUtilisateur) {
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idArticle=idArticle;
        this.idUtilisateur=idUtilisateur;
    }
    public Commentaire(int idCommentaire,String contenuCommentaire){
        this.idCommentaire=idCommentaire;
        this.contenuCommentaire=contenuCommentaire;
    }
    public Commentaire(Article article) {
        this.article = article;
    }
    public Commentaire(Utilisateur utilisateur){this.utilisateur=utilisateur;}

    public Article getArticle() {
        return article;
    }
    public Utilisateur getUtilisateur(){
        return utilisateur;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setUtilisateur(Utilisateur utilisateur){this.utilisateur=utilisateur;}


    public int getIdCommentaire() {
        return idCommentaire;

    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
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

    public int getEtatCommentaire() {
        return etatCommentaire;
    }

    public void setEtatCommentaire(int etatCommentaire) {
        this.etatCommentaire = etatCommentaire;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public Article setIdArticle(int idArticle) {
        this.idArticle = idArticle;
        return null;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public Utilisateur setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        return null;
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
