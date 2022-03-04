package Entities;

public class Article {
    private int idArticle;
    private String titreArticle;
    private String descriptionArticle;
    private String imageArticle;
    private String dateArticle;
    private Categorie categorie;
    private Utilisateur utilisateur;
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
        this.dateArticle = dateArticle;
        this.idUtilisateur=idUtilisateur;

    }

    /* Constructor with NO ID*/
    public Article( String titreArticle, String descriptionArticle, String imageArticle, String dateArticle,int idCategorie,int idUtilisateur) {
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.imageArticle = imageArticle;
        this.dateArticle = dateArticle;
        this.idUtilisateur=idUtilisateur;

    }

    public Article(Categorie categorie) {
        this.categorie = categorie;
    }
    public Article(Utilisateur utilisateur){this.utilisateur=utilisateur;}

    public Categorie getCategorie() {
        return categorie;
    }
    public Utilisateur getUtilisateur(){
        return utilisateur;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public void setUtilisateur(Utilisateur utilisateur){this.utilisateur=utilisateur;}

    public int getIdCategorie() {
        return idCategorie;
    }

    public Categorie setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
        return null;
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



    public String getDateArticle() {
        return dateArticle;
    }

    public void setDateArticle(String dateArticle) {
        this.dateArticle = dateArticle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public Utilisateur setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        return null;
    }

}
