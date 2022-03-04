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
        String query = "INSERT INTO commentaire(contenu_commentaire,date_commentaire,etat_commentaire,id_article,id_utilisateur)" + "VALUES(?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,Commentaire.getContenuCommentaire());
            ste.setString(2,Commentaire.getDateCommentaire());
            ste.setInt(3,Commentaire.getEtatCommentaire());
            ste.setInt(4,Commentaire.getIdArticle());
            ste.setInt(5,Commentaire.getIdUtilisateur());


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
                commentaire.setContenuCommentaire(rs.getString("contenu_commentaire"));
                commentaire.setDateCommentaire(rs.getString("date_commentaire"));
                //commentaire.setEtatCommentaire(rs.getInt("etat_commentaire"));
                commentaire.setArticle(commentaire.setIdArticle(rs.getInt("id_article")));
                commentaire.setUtilisateur(commentaire.setIdUtilisateur(rs.getInt("id_utilisateur")));

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
        String query = "UPDATE commentaire SET contenu_commentaire = '" + commentaire.getContenuCommentaire()+
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