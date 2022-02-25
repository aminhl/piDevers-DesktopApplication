package Services;


import Entities.Museum;
import Entities.Reservation;
import Services.IService;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationMuseumService implements IService<Reservation> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_museum,id_us) VALUES(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getIdMuseum());
            ste.setInt(2,reservation.getIdUs());
            ste.executeUpdate();
            System.out.println("Participation Museum Added Successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Museum> listMuseumParticipes = FXCollections.observableArrayList();
        String query = "SELECT *,R.id_museum from museum as R INNER JOIN reservation as re" +
                " ON re.id_museum = R.id_museum";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Museum museum1 = new Museum();
                museum1.setMuseumId(rs.getInt("id_museum"));
                museum1.setNomMuseum(rs.getString("nom_museum"));
                museum1.setImageMuseum(rs.getString("image_museum"));
                museum1.setCreatedAt(rs.getDate("created_at"));
                museum1.setUpdatedAt(rs.getDate("updated_at"));
                museum1.setRating(rs.getInt("rating_museum"));
                museum1.setLocalisationMuseum(rs.getInt("locallisation_museum"));
                listMuseumParticipes.add(museum1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listMuseumParticipes;
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



    public ResultSet retreiveReservedMuseum(String s){
        int idEv = 0;
        String query = "SELECT *,R.id_museum from museum as M INNER JOIN reservation as re\" +\n" +
                "                \" ON re.id_museum = M.id_museum WHERE nom_museum = '" + s + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            return rs;
        }   catch (SQLException ex) {
            Logger.getLogger(Services.ReservationMuseumService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    Package Services;
}


