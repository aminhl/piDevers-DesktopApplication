package Services;

import Entities.Randonnee;
import Tools.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RandonneeService implements IService<Randonnee> {
    Connection c = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Randonnee randonnee) {

        try {
            PreparedStatement req = c.prepareStatement("insert into randonnee(nom_randonnee,pays_randonnee,date_randonnee,created_at,updated_at,image_endroit_randonnee)values(?,?,?,?,?,?)");
            req.setString(1, randonnee.getNomRandonnee());
            req.setString(2, randonnee.getPaysRandonnee());
            req.setDate(3, new java.sql.Date(randonnee.getDateRandonnee().getTime()));
            req.setDate(4, new java.sql.Date(randonnee.getCreated_at().getTime()));
            req.setDate(5, new java.sql.Date(randonnee.getUpdated_at().getTime()));
            req.setString(6, randonnee.getImageEndroitRandonnee());
            req.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Randonnee> afficher() {
        List<Randonnee> listRandonnees = new ArrayList<>();
        try {
            PreparedStatement req = c.prepareStatement("select * from randonnee  ");
            ResultSet rs = req.executeQuery();
            while (rs.next()) {
                Randonnee randonnee1 = new Randonnee();
                randonnee1.setIdRandonnee(rs.getInt("id_randonnee"));
                randonnee1.setNomRandonnee(rs.getString("nom_randonnee"));
                randonnee1.setPaysRandonnee(rs.getString("pays_randonnee"));
                randonnee1.setDateRandonnee(rs.getDate("date_randonnee"));
                randonnee1.setCreated_at(rs.getDate("created_at"));
                randonnee1.setUpdated_at(rs.getDate("updated_at"));
                randonnee1.setImageEndroitRandonnee(rs.getString("image_endroit_randonnee")); //We either put image columns as string to save space or as blob .
                listRandonnees.add(randonnee1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listRandonnees;
    }

    @Override
    public void modifier(Randonnee randonnee) {
        try {
            PreparedStatement req = c.prepareStatement("update randonnee set nom_randonnee=?,pays_randonnee=? ,date_randonnee=?,created_at=?,updated_at=?,image_endroit_randonnee=? " +
                    "where idRandonnee= '"+randonnee.getIdRandonnee()+"'");
            req.setString(1, randonnee.getNomRandonnee());
            req.setString(2,randonnee.getPaysRandonnee());
            req.setDate(3, new java.sql.Date( randonnee.getDateRandonnee().getTime()));
            req.setDate(4, new java.sql.Date(randonnee.getCreated_at().getTime()));
            req.setDate(5, new java.sql.Date(randonnee.getUpdated_at().getTime()));
            req.setString(6, randonnee.getImageEndroitRandonnee());
            req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimer(Randonnee randonnee) {
        try {
            PreparedStatement req = c.prepareStatement("delete from Randonnee where id_randonnee = ? ");
            req.setInt(1, randonnee.getIdRandonnee());

            req.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

