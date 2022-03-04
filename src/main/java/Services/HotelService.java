package Services;

import Entities.Hotel;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class HotelService implements IService<Hotel> {
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Hotel hotel) {
        String query="INSERT INTO HOTEL(nom_hotel,adresse_hotel,email_hotel,etoile_hotel,telephone_hotel," +
                "prix_hotel," +
                "image_hotel) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,hotel.getNomHotel());
            ste.setString(2,hotel.getAdresseHotel());
            ste.setString(3,hotel.getEmailHotel());
            ste.setInt(   4,hotel.getEtoileHotel());
            ste.setString(5,hotel.getTelephoneHotel());
            ste.setFloat(6,hotel.getPrixHotel());
            ste.setString(7,hotel.getImageHotel());
            ste.executeUpdate();
            System.out.println("Hotel Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Hotel> afficher() {
        ObservableList<Hotel> listHotels = FXCollections.observableArrayList();
        String query = "SELECT * FROM HOTEL";
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
                listHotels.add(hotel);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listHotels;
    }

    @Override
    public void modifier(Hotel hotel) {
        String query = "UPDATE HOTEL SET nom_hotel = '" + hotel.getNomHotel() + "', adresse_hotel = '" +
                hotel.getAdresseHotel() + "', email_hotel = '" + hotel.getEmailHotel() + "', etoile_hotel ="
                + hotel.getEtoileHotel() + ", telephone_hotel= '" + hotel.getTelephoneHotel() + "', prix_hotel =" +
                hotel.getPrixHotel() + ", image_hotel = '" + hotel.getImageHotel() +
                "' WHERE id_hotel = " + hotel.getIdHotel() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Hotel Modified Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Hotel hotel) {
        String query ="DELETE FROM HOTEL WHERE id_hotel = " + hotel.getIdHotel() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Hotel Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
