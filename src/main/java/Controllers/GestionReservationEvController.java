package Controllers;

import Entities.Evenement;
import Entities.Reservation;
import Entities.Utilisateur;
import Services.EvenementService;
import Services.ReservationEvService;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import com.itextpdf.text.DocumentException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionReservationEvController implements Initializable {
    Connection cnx = MaConnexion.getInstance().getCnx();
    public static final String ACCOUNT_SID = "AC02908980344cad799b56226aeb5ccf96";
    public static final String AUTH_TOKEN = "0c96f35933aab8618fdbe45fd5b3418b";

    @FXML
    private Button btnAjouterParticipationEv;

    @FXML
    private Button btnSupprimerParticipationEv;

    @FXML
    private TableColumn<Evenement, String> colLieuParticipationEv;

    @FXML
    private TableColumn<Evenement, Integer> colNombreParticipationEv;

    @FXML
    private TableColumn<Evenement, String> colTitreParticipationEv;

    @FXML
    private TableColumn<Evenement, String> colTypeParticipationEv;

    @FXML
    private ComboBox<String> comboTitreParticipationEv;

    @FXML
    private ComboBox<String> comboTypeParticipationEv;

    @FXML
    private Spinner<Integer> spinnerNombreParticipationEv;

    @FXML
    private TableView<Evenement> tvParticipationEv;

    @FXML
    private Button exportResEv;

    @FXML
    private PieChart piechartrev;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    Reservation r = new Reservation();
    ReservationEvService revs = new ReservationEvService();
    Evenement e = new Evenement();
    EvenementService es = new EvenementService();
    Evenement myEv = new Evenement();


    public void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1087, 649);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        piechartrev.setData(piechartdata);
        btnAjouterParticipationEv.setTextFill(Color.WHITE);
        btnSupprimerParticipationEv.setTextFill(Color.WHITE);
        ObservableList titresServices = FXCollections.observableArrayList(revs.getTitresEv());
        comboTitreParticipationEv.setItems(titresServices);
        comboTitreParticipationEv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            myEv = revs.getEventForReservation(newValue);
        });
       showParticipationEv();
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterParticipationEv)
            ajouterReservationEv();
        if (event.getSource() == btnSupprimerParticipationEv)
            supprimerReservationEv();
    }

    @FXML
    private void ajouterReservationEv(){
        try{
            Utilisateur u = new Utilisateur();
            UtilisateurService us = new UtilisateurService();
            u = (Utilisateur) UserSession.INSTANCE.get("user");
            r.setEv(myEv);
            r.setUtilisateur(u);
            revs.ajouter(r);
            loadData();
            piechartrev.setData(piechartdata);
            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+216"+u.getNumero_telephoneUtilisateur()),
                    new PhoneNumber("+19035467504"),
                    "Salut Mr/Mme," +  u.getPrenomUtilisateur() + " " + u.getNomUtilisateur() +
                            "Votre Reservation d'Evenement " + myEv.getTitreEvenement() + " est bien etablie").create();
            showParticipationEv();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showParticipationEv() {
        ObservableList<Evenement> listReservationEv = revs.afficher();
        try{
            colTitreParticipationEv.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titreEvenement"));
            colLieuParticipationEv.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuEvenement"));
            colTypeParticipationEv.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeEvenement"));
            //colNombreParticipationEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("nombreParticipation"));*/
            tvParticipationEv.setItems(listReservationEv);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void supprimerReservationEv() {
        e = tvParticipationEv.getSelectionModel().getSelectedItem();
        r.setEv(e);
        revs.supprimer(r);
        loadData();
        piechartrev.setData(piechartdata);
        showParticipationEv();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        try{
            Evenement evp = tvParticipationEv.getSelectionModel().getSelectedItem();
            comboTitreParticipationEv.setValue(evp.getTitreEvenement());
        }catch (Exception e){
            System.out.println("");
        }
    }

    @FXML
    public void pdfResEv(ActionEvent event) throws DocumentException, FileNotFoundException {
        revs.pdfReservationEv();
    }

    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();

    private void loadData() {
        String query = "select COUNT(*) as count,e.titre_evenement" +
                " From evenement e,reservation rev WHERE e.id_evenement=rev.id_event GROUP BY rev.id_event"; //ORDER BY asc
        piechartdata = FXCollections.observableArrayList();
        cnx = MaConnexion.getInstance().getCnx();
        try {
            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("titre_evenement"),rs.getInt("count")));
                p.add(rs.getString("e.titre_evenement"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
