package Services;

import Entities.Evenement;
import Entities.Reservation;
import Tools.MaConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public class ReservationEvService implements IService<Reservation> {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Reservation reservation) {
        String query = "INSERT INTO RESERVATION(id_event,id_us) VALUES(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservation.getEv().getIdEvenement());
            ste.setInt(2,reservation.getUtilisateur().getIdUtilisateur());
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
                ep.setDateEvenement(rs.getDate("date_evenement"));
                ep.setImageEvenement(rs.getString("image_evenement"));
                listEvenementsParticipes.add(ep);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listEvenementsParticipes;
    }

    @Override
    public void modifier(Reservation reservation) {}

    @Override
    public void supprimer(Reservation reservation) {
        String query = "DELETE FROM RESERVATION WHERE id_event = " + reservation.getEv().getIdEvenement() + " LIMIT 1";
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

    public Evenement getEventForReservation(String s){
        String query = "SELECT * FROM EVENEMENT WHERE titre_evenement= '" + s +"' LIMIT 1";
        Evenement evenement = new Evenement();
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                evenement.setIdEvenement(rs.getInt("id_evenement"));
                evenement.setTitreEvenement(rs.getString("titre_evenement"));
                evenement.setLieuEvenement(rs.getString("lieu_evenement"));
                evenement.setTypeEvenement(rs.getString("type_evenement"));
                evenement.setDescriptionEvenement(rs.getString("description_evenement"));
                evenement.setDateEvenement(rs.getDate("date_evenement"));
                evenement.setImageEvenement(rs.getString("image_evenement"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return evenement;
    }

    public void pdfReservationEv() throws FileNotFoundException, DocumentException{
        try {
            Reservation r = new Reservation();
            String file_name="C:\\xampp\\htdocs\\PideversImgUploaded\\EventsReservation.pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_name));
            doc.open();
            PreparedStatement ps=null;
            ResultSet rs=null;
            String query="SELECT *,reservation.id_event from evenement INNER JOIN reservation ON id_event = id_evenement\n";
            ps=cnx.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()) {
                Paragraph para=new Paragraph(
                        rs.getString("titre_evenement")+" "+
                                rs.getString("lieu_evenement")+" " +rs.getString("type_evenement")+" "+
                                rs.getString("date_evenement")+" "
                );
                doc.add(para);
                doc.add(new Paragraph(" "));
            }
            doc.close();
            System.out.println("Reservation Events Pdf Generated With Success");
        }catch(Exception k){
            System.err.println(k);
        }
    }


}
