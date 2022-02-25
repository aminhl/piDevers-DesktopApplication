package Services;

import Entities.Article;
import Entities.Categorie;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class ArticleService implements IService<Article>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Article article) {
        String query = "INSERT INTO article(titre_article,description_article,image_article,date_article,id_categorie,id_utilisateur)" + "VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,article.getTitreArticle());
            ste.setString(2,article.getDescriptionArticle());
            ste.setString(3,article.getImageArticle());
            ste.setString(4, article.getDateArticle());
            ste.setInt(5, article.getidCategorie());
            ste.setInt(6, article.getidUtilisateur());

            ste.executeUpdate();
            System.out.println("article Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Article> afficher() {
        ObservableList<Article> listArticles = FXCollections.observableArrayList();
        String query = "SELECT * FROM article";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Article article = new Article();
                article.setIdArticle(rs.getInt("id_article"));
                article.setTitreArticle(rs.getString("titre_article"));
                article.setDescriptionArticle(rs.getString("description_article"));
                article.setImageArticle(rs.getString("image_article"));
                article.setDateArticle(rs.getString("date_article"));
                article.setIdCategorie(rs.getInt("id_categorie"));
                article.setIdUtilisateur(rs.getInt("id_utilisateur"));

                listArticles.add(article);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listArticles;
    }

    @Override
    public void modifier(Article article) {
        String query = "UPDATE article SET titre_article = '" + article.getTitreArticle() + "', description_article = '" +
                article.getDescriptionArticle() + "', image_article = '" + article.getImageArticle() +
                "' WHERE id_article = " + article.getIdArticle() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Article Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Article article) {
        String query = "DELETE FROM article WHERE article.id_article = " + article.getIdArticle() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Article Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}