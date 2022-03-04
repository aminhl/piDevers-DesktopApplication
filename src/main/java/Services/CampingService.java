package Services;

import Entities.Camping;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CampingService implements IService<Camping>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Camping camping) {
        String query = "INSERT INTO CAMPING(nom_camping,adresse_camping,image_camping,telephone_camping," +
                "rating_camping,description_camping,id_user) " +
                "VALUES(?,?,?,?,?,?,0)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,camping.getNomCamping());
            ste.setString(2,camping.getAdresseCamping());
            ste.setString(3,camping.getImageCamping());
            ste.setString(4,camping.getTelephoneCamping());
            ste.setFloat(5,camping.getRatingCamping());
            ste.setString(6,camping.getDescriptionCamping());
            ste.executeUpdate();
            System.out.println("Camping Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Camping> afficher() {
        ObservableList<Camping> listCamping = FXCollections.observableArrayList();
        String query = "SELECT * FROM camping";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Camping camping = new Camping();
                camping.setIdCamping(rs.getInt("id_camping"));
                camping.setNomCamping(rs.getString("nom_camping"));
                camping.setAdresseCamping(rs.getString("adresse_camping"));
                camping.setImageCamping(rs.getString("image_camping"));
                camping.setTelephoneCamping(rs.getString("telephone_camping"));
                camping.setRatingCamping(rs.getFloat("rating_camping"));
                camping.setDescriptionCamping(rs.getString("description_camping"));
                listCamping.add(camping);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listCamping;
    }

    @Override
    public void modifier(Camping camping) {
        String query = "UPDATE CAMPING SET nom_camping = '" + camping.getNomCamping() + "', adresse_camping = '" +
                camping.getAdresseCamping() + "', image_camping = '" + camping.getImageCamping() +
                "', telephone_camping = '" + camping.getTelephoneCamping() + "', rating_camping = '" + camping.getRatingCamping() +
                "',description_camping = '" + camping.getDescriptionCamping()  +
                "' WHERE id_camping = " + camping.getIdCamping() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Camping Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Camping camping) {
        String query = "DELETE FROM CAMPING WHERE id_camping = " + camping.getIdCamping() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Camping Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
