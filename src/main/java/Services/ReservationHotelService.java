package Services;

import Entities.Evenement;
import Entities.Hotel;
import Entities.Reservation;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ReservationHotelService implements IService<Reservation> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_event, id_camp, id_hotel, id_us) VALUES (0,0,?,5)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getIdHotel());
            ste.executeUpdate();
            System.out.println("Added Successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Hotel> listHotelReservation = FXCollections.observableArrayList();
      String query = "SELECT *,r.id_hotel from hotel as h inner join reservation as r on h.id_hotel = r.id_hotel ";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Hotel hotel = new Hotel();
                hotel.setIdHotel(rs.getInt("id_hotel"));
                hotel.setNomHotel(rs.getString("nom_hotel"));
                hotel.setAdresseHotel(rs.getString("adresse_hotel"));
                hotel.setEmailHotel(rs.getString("email_hotel"));
                hotel.setEtoileHotel(rs.getInt("etoile_hotel"));
                hotel.setTelephoneHotel(rs.getString("telephone_hotel"));
                hotel.setPrixHotel(rs.getFloat("prix_hotel"));
                hotel.setImageHotel(rs.getString("image_hotel"));
                listHotelReservation.add(hotel);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listHotelReservation;
    }

    @Override
    public void modifier(Reservation reservation) {

    }

    @Override
    public void supprimer(Reservation reservation) {
        String query = "DELETE FROM RESERVATION WHERE id_hotel = " + reservation.getIdHotel() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Booking Removed Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
