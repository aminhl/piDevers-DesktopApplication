package Services;

import Entities.Evenement;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Random;

public class EvenementService implements IService<Evenement>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Evenement evenement) {
        String query = "INSERT INTO EVENEMENT(titre_evenement,lieu_evenement,type_evenement,description_evenement,date_evenement," +
                 "image_evenement,id_user) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,evenement.getTitreEvenement());
            ste.setString(2,evenement.getLieuEvenement());
            ste.setString(3,evenement.getTypeEvenement());
            ste.setString(4,evenement.getDescriptionEvenement());
            ste.setDate(5, evenement.getDateEvenement());
            ste.setString(6, evenement.getImageEvenement());
            Random rand = new Random();
            int n = rand.nextInt(10);
            ste.setInt(7, n);
            ste.executeUpdate();
            System.out.println("Evenement Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> afficher() {
        ObservableList<Evenement> listEvenements = FXCollections.observableArrayList();
        String query = "SELECT * FROM EVENEMENT";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Evenement evenement = new Evenement();
                evenement.setIdEvenement(rs.getInt("id_evenement"));
                evenement.setTitreEvenement(rs.getString("titre_evenement"));
                evenement.setLieuEvenement(rs.getString("lieu_evenement"));
                evenement.setTypeEvenement(rs.getString("type_evenement"));
                evenement.setDescriptionEvenement(rs.getString("description_evenement"));
                evenement.setDateEvenement(rs.getDate("date_evenement"));
                evenement.setImageEvenement(rs.getString("image_evenement"));
                listEvenements.add(evenement);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listEvenements;
    }

    @Override
    public void modifier(Evenement evenement) {
        String query = "UPDATE EVENEMENT SET titre_evenement = '" + evenement.getTitreEvenement() + "', lieu_evenement = '" +
                evenement.getLieuEvenement() + "', type_evenement = '" + evenement.getTypeEvenement() + "', description_evenement = '" +
                evenement.getDescriptionEvenement() + "', date_evenement= '" + evenement.getDateEvenement()
                + "' WHERE id_evenement = " + evenement.getIdEvenement() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Evenement Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement evenement) {
        String query = "DELETE FROM EVENEMENT WHERE id_evenement = " + evenement.getIdEvenement() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Evenement Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
