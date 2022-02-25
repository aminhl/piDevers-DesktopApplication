package Services;

import Entities.Museum;
import Entities.Randonnee;
import Tools.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuseumServices implements IService<Museum> {

    Connection c = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Museum museum) {
        try {
            PreparedStatement req = c.prepareStatement("insert into museum(nom_museum," +
                    "locallisation_museum," +
                    "created_at," +
                    "updated_at," +
                    "rating_museum," +
                    "image_museum)values(?,?,?,?,?,?)");
            req.setString(1, museum.getNomMuseum());
            req.setInt(2, museum.getLocalisationMuseum());
            req.setDate(3, new java.sql.Date(museum.getCreatedAt().getTime()));
            req.setDate(4, new java.sql.Date(museum.getUpdatedAt().getTime()));
            req.setInt(5, museum.getRating());
            req.setString(6,museum.getImageMuseum());
            req.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Museum> afficher() {
        List<Museum> listMuseum = new ArrayList<>();
        try {
            PreparedStatement req = c.prepareStatement("select * from museum  ");
            ResultSet rs = req.executeQuery();
            while (rs.next()) {
                Museum museum = new Museum();
                museum.setMuseumId(rs.getInt("id_museum"));
                museum.setNomMuseum(rs.getString("nom_museum"));
                museum.setLocalisationMuseum(rs.getInt("locallisation_museum"));
                museum.setCreatedAt(rs.getDate("created_at"));
                museum.setUpdatedAt(rs.getDate("updated_at"));
                museum.setRating(rs.getInt("rating_museum"));
                museum.setImageMuseum(rs.getString("image_museum")); //We either put image columns as string to save space or as blob .
                listMuseum.add(museum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listMuseum;
    }

    @Override
    public void modifier(Museum museum) {

        try {
            PreparedStatement req = c.prepareStatement("update museum set nom_museum=?,locallisation_museum=? ,updated_at=?,rating_museum=?,image_museum=? " +
                    "where id_museum= '"+museum.getMuseumId()+"'");
            req.setString(1, museum.getNomMuseum());
            req.setInt(2,museum.getLocalisationMuseum());
            req.setDate(3, new java.sql.Date(museum.getUpdatedAt().getTime()));
            req.setInt(4, museum.getRating());
            req.setString(5,museum.getImageMuseum());
            req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimer(Museum museum) {
        try {
            PreparedStatement req = c.prepareStatement("delete from museum where id_museum = ? ");
            req.setInt(1, museum.getMuseumId());

            req.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}


