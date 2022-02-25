package Services;

import Entities.Categorie;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class CategorieService implements IService<Categorie>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Categorie categorie) {
        String query = "INSERT INTO categorie (nom_categorie)" + "VALUES(?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,categorie.getNomCategorie());


            ste.executeUpdate();
            System.out.println("Categorie Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Categorie> afficher() {
        ObservableList<Categorie> listCategories= FXCollections.observableArrayList();
        String query = "SELECT * FROM categorie";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(rs.getInt("id_commentaire"));
                categorie.setNomCategorie(rs.getString("nom_categorie"));

                listCategories.add(categorie);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listCategories;
    }

    @Override
    public void modifier(Categorie categorie) {
        String query = "UPDATE categorie SET nom_categorie = '" + categorie.getNomCategorie() +
                "' WHERE id_categorie = "+categorie.getIdCategorie()+"";
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
    public void supprimer(Categorie categorie) {
        String query = "DELETE FROM categoire WHERE id_categoire = " + categorie.getIdCategorie() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Categorie Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}