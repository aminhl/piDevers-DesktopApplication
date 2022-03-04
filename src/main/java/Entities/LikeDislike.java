package Entities;

public class LikeDislike {
    private int idLikeDislike;
    private Utilisateur utilisateur;
    private Article article;
    private int idUtilisateur;
    private int idArticle;
    private boolean isLike;


    public LikeDislike() {
    }
    /* Constructor with ID*/

    public LikeDislike(int idLikeDislike, int idUtilisateur, int idArticle, boolean isLike) {
        this.idLikeDislike = idLikeDislike;
        this.isLike = isLike;
        this.idArticle = idArticle;
        this.idUtilisateur = idUtilisateur;
    }

    /* Constructor with NO ID*/
    public LikeDislike( int idUtilisateur, int idArticle, boolean isLike) {
        this.isLike = isLike;
        this.idArticle = idArticle;
        this.idUtilisateur = idUtilisateur;
    }
    public LikeDislike(Article article){this.article = article;}
    public LikeDislike(Utilisateur utilisateur){this.article = article;}



    public int getIdLikeDislike() {
        return idLikeDislike;
    }

    public void setIdLikeDislike(int idLikeDislike) {
        this.idLikeDislike = idLikeDislike;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
