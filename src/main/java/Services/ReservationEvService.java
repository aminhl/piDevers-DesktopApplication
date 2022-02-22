package Services;

import Entities.Evenement;
import Entities.Reservation;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.List;

public class ReservationEvService implements IService<Reservation> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_event,id_camp,id_hotel,id_us) VALUES(?,0,0,30)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getIdEvent());
            ste.executeUpdate();
            System.out.println("Participation Evenement Added Successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Evenement> listEvenementsParticipes = FXCollections.observableArrayList();
        String query = "SELECT *,reservation.id_event from evenement INNER JOIN reservation ON id_event = id_evenement";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Evenement ep = new Evenement();
                ep.setIdEvenement(rs.getInt("id_evenement"));
                ep.setTitreEvenement(rs.getString("titre_evenement"));
                ep.setLieuEvenement(rs.getString("lieu_evenement"));
                ep.setTypeEvenement(rs.getString("type_evenement"));
                ep.setDescriptionEvenement(rs.getString("description_evenement"));
                ep.setDateEvenement(rs.getString("date_evenement"));
                ep.setImageEvenement(rs.getString("image_evenement"));
                listEvenementsParticipes.add(ep);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listEvenementsParticipes;
    }

    @Override
    public void modifier(Reservation reservation) {

    }

    @Override
    public void supprimer(Reservation reservation) {
        String query = "DELETE FROM RESERVATION WHERE id_event = " + reservation.getIdEvent() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Participation Evenement Removed Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<String> getTitresEv(){
        ObservableList<String> titresEvenement = FXCollections.observableArrayList();
        String query = "SELECT titre_evenement FROM EVENEMENT";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                Evenement e = new Evenement();
                titresEvenement.add(rs.getString("titre_evenement"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return titresEvenement;
    }

    public Integer retreiveIdEvent(String s){
        int idEv = 0;
        String query = "SELECT id_evenement FROM EVENEMENT WHERE titre_evenement = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
                idEv = (rs.getInt("id_evenement"));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idEv;
    }

}
