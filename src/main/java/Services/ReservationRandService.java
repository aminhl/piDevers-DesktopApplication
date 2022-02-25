package Services;

import Entities.Randonnee;
import Entities.Reservation;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationRandService implements IService<Reservation> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_randonnee,id_us) VALUES(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getIdRandonnee());
            ste.setInt(2,reservation.getIdUs());
            ste.executeUpdate();
            System.out.println("Participation Randonnee Added Successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Randonnee> listRandonneeParticipes = FXCollections.observableArrayList();
        String query = "SELECT *,R.id_randonnee from randonnee as R INNER JOIN reservation as re" +
                " ON re.id_randonnee = R.id_randonnee";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Randonnee randonnee1 = new Randonnee();
                randonnee1.setIdRandonnee(rs.getInt("id_randonnee"));
                randonnee1.setNomRandonnee(rs.getString("nom_randonnee"));
                randonnee1.setPaysRandonnee(rs.getString("pays_randonnee"));
                randonnee1.setDateRandonnee(rs.getDate("date_randonnee"));
                randonnee1.setCreated_at(rs.getDate("created_at"));
                randonnee1.setUpdated_at(rs.getDate("updated_at"));
                randonnee1.setImageEndroitRandonnee(rs.getString("image_endroit_randonnee"));
                listRandonneeParticipes.add(randonnee1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listRandonneeParticipes;
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



    public ResultSet retreiveReservedRandonnee(String s){
        int idEv = 0;
        String query = "SELECT *,R.id_randonnee from randonnee as R INNER JOIN reservation as re\" +\n" +
                "                \" ON re.id_randonnee = R.id_randonnee WHERE nom_randonnee = '" + s + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            return rs;
        }   catch (SQLException ex) {
                Logger.getLogger(ReservationRandService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
        }

    }


