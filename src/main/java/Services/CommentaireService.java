package Services;

import Entities.Commentaire;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class CommentaireService implements IService<Commentaire>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Commentaire Commentaire) {
        String query = "INSERT INTO commentaire(auteur_commentaire,contenu_commentaire,date_commentaire,etat_commentaire,id_article,id_utilisateur)" + "VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,Commentaire.getAuteurCommentaire());
            ste.setString(2,Commentaire.getContenuCommentaire());
            ste.setString(3,Commentaire.getDateCommentaire());
            ste.setString(4,Commentaire.getEtatCommentaire());
            ste.setInt(5,Commentaire.getIdArticle());
            ste.setInt(6,Commentaire.getIdUtilisateur());


            ste.executeUpdate();
            System.out.println("Commentaire Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Commentaire> afficher() {
        ObservableList<Commentaire> listCommentaires= FXCollections.observableArrayList();
        String query = "SELECT * FROM commentaire";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Commentaire commentaire = new Commentaire();
                commentaire.setIdCommentaire(rs.getInt("id_commentaire"));
                commentaire.setAuteurCommentaire(rs.getString("auteur_commentaire"));
                commentaire.setContenuCommentaire(rs.getString("contenu_commentaire"));
                commentaire.setDateCommentaire(rs.getString("date_commentaire"));
                commentaire.setEtatCommentaire(rs.getString("etat_commentaire"));
                commentaire.setIdArticle(rs.getInt("id_article"));
                commentaire.setIdUtilisateur(rs.getInt("id_utilisateur"));

                listCommentaires.add(commentaire);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listCommentaires;
    }

    @Override
    public void modifier(Commentaire commentaire) {
        String query = "UPDATE commentaire SET contenu_commentaire = '" + commentaire.getContenuCommentaire()+"test test" +
                "', etat_commentaire = '" + commentaire.getEtatCommentaire() +
                "' WHERE id_commentaire = "+commentaire.getIdCommentaire()+"";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Commentaire commentaire) {
        String query = "DELETE FROM commentaire WHERE id_commentaire = " + commentaire.getIdCommentaire() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}