package Services;

import Entities.Camping;
import Entities.Reservation;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ReservationCpService implements IService<Reservation>{
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_camp,id_us) VALUES(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getCamping().getIdCamping());
            ste.setInt(2,reservation.getUtilisateur().getIdUtilisateur());
            ste.executeUpdate();
            System.out.println("Participation Camping Added Successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Camping> listCampingsParticipes = FXCollections.observableArrayList();
        String query = "SELECT *,reservation.id_camp from camping INNER JOIN reservation ON id_camp = id_camping";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Camping cp = new Camping();
                cp.setIdCamping(rs.getInt("id_camping"));
                cp.setNomCamping(rs.getString("nom_camping"));
                cp.setAdresseCamping(rs.getString("adresse_camping"));
                cp.setImageCamping(rs.getString("image_camping"));
                cp.setTelephoneCamping(rs.getString("telephone_camping"));
                listCampingsParticipes.add(cp);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listCampingsParticipes;
    }

    @Override
    public void modifier(Reservation reservation) {

    }

    @Override
    public void supprimer(Reservation reservation) {
        String query = "DELETE FROM RESERVATION WHERE id_camp = " + reservation.getCamping().getIdCamping() + " limit 1";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Participation Camping Removed Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<String> getNomsCp(){
        ObservableList<String> nomCampings = FXCollections.observableArrayList();
        String query = "SELECT nom_camping FROM CAMPING";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                Camping c = new Camping();
                nomCampings.add(rs.getString("nom_camping"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return nomCampings;
    }

    public Integer retreiveIdCp(String s){
        int idCp = 0;
        String query = "SELECT id_camping FROM CAMPING WHERE nom_camping = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
                idCp = (rs.getInt("id_camping"));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idCp;
    }
    public Camping retreiveCp(String s){
        Camping Cp =new Camping();
        String query = "SELECT * FROM CAMPING WHERE nom_camping = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
            {
                Cp.setIdCamping(rs.getInt("id_camping"));
                Cp.setNomCamping(rs.getString("nom_camping"));
                Cp.setAdresseCamping(rs.getString("adresse_camping"));
                Cp.setImageCamping(rs.getString("image_camping"));
                Cp.setTelephoneCamping(rs.getString("telephone_camping"));
                Cp.setRatingCamping(rs.getFloat("rating_camping"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Cp;
    }
}
