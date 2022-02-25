package Services;

import Entities.Utilisateur;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.List;

public class UtilisateurService implements IService<Utilisateur> {
    Connection cnx = MaConnexion.getInstance().getCnx();
    boolean existe=false;
    /*public void Dejaexiste(Utilisateur utilisateur) {
        String query = "SELECT COUNT(*) from utilisateur WHERE login_utilisateur = " + utilisateur.getLoginUtilisateur() +"";
        try{
            Statement ste = cnx.createStatement();
            ResultSet res=ste.executeQuery(query);
            int nbr=res.getRow();
            System.out.println(nbr);
          //  System.out.println(res);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
*/
    public int existe(Utilisateur u) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from utilisateur WHERE login_utilisateur = '" + u.getLoginUtilisateur() +"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
       String query = "INSERT INTO UTILISATEUR(id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,login_utilisateur," +
              "pwd_utilisateur,img_utilisateur,rank_utilisateur,numTel_utilisateur,adresse_utilisateur) VALUES(?,?,?,?,?,?,?,?,?,?)";

 int x=existe(utilisateur);
    if (x==0)
    {
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,utilisateur.getIdUtilisateur());
            ste.setString(2,utilisateur.getNomUtilisateur());
            ste.setString(3,utilisateur.getPrenomUtilisateur());
            ste.setString(4,utilisateur.getEmailUtilisateur());
            ste.setString(5,utilisateur.getLoginUtilisateur());
            ste.setString(6,utilisateur.getMot_de_passeUtilisateur());
            ste.setString(7,utilisateur.getImgUtilisateur());
            ste.setInt(8,utilisateur.getRankUtilisateur());
            ste.setString(9,utilisateur.getNumero_telephoneUtilisateur());
            ste.setString(10,utilisateur.getAdresseUtilisateur());
            ste.executeUpdate();
            System.out.println("User Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }else
    {
        System.out.println("user already exists");
    }
    }

    @Override
    public List<Utilisateur> afficher() {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
        while(rs.next())
        {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
            utilisateur.setNomUtilisateur(rs.getString("nom_utilisateur"));
            utilisateur.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
            utilisateur.setEmailUtilisateur(rs.getString("email_utilisateur"));
            utilisateur.setLoginUtilisateur(rs.getString("login_utilisateur"));
            utilisateur.setMot_de_passeUtilisateur(rs.getString("pwd_utilisateur"));
            utilisateur.setImgUtilisateur(rs.getString("img_utilisateur"));
            utilisateur.setRankUtilisateur(rs.getInt("rank_utilisateur"));
            utilisateur.setNumero_telephoneUtilisateur(rs.getString("numTel_utilisateur"));
            utilisateur.setAdresseUtilisateur(rs.getString("adresse_utilisateur"));
            users.add(utilisateur);
        }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void modifier(Utilisateur utilisateur) {
        String query = "UPDATE utilisateur SET " +
                "nom_utilisateur = '" + utilisateur.getNomUtilisateur() +
                "', prenom_utilisateur = '" + utilisateur.getPrenomUtilisateur() +
                "', email_utilisateur = '" + utilisateur.getEmailUtilisateur() +
                "', login_utilisateur = '" + utilisateur.getLoginUtilisateur() +
                "', pwd_utilisateur= '" + utilisateur.getMot_de_passeUtilisateur() +
                "', img_utilisateur = '" + utilisateur.getImgUtilisateur() +
                "', rank_utilisateur = '" + utilisateur.getRankUtilisateur() +
                "', numTel_utilisateur = '" + utilisateur.getNumero_telephoneUtilisateur() +
                "', adresse_utilisateur = '" + utilisateur.getAdresseUtilisateur() +
                "' WHERE id_utilisateur = " + utilisateur.getIdUtilisateur() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("User Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void supprimer(Utilisateur utilisateur) {
        String query = "DELETE FROM utilisateur WHERE login_utilisateur = '" + utilisateur.getLoginUtilisateur() + "'";
        try{
            Statement ste = cnx.createStatement();
            int deleted=ste.executeUpdate(query);
            System.out.println(deleted);
            if(deleted > 0)
                System.out.println("Deleted successfully");
            else
                System.out.println("Nothing deleted");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
