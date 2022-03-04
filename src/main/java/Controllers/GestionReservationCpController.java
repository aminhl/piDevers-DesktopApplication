package Controllers;

import Entities.Camping;

import Entities.Reservation;
import Entities.Utilisateur;
import Services.ReservationCpService;

import Services.UserSession;
import Services.UtilisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionReservationCpController implements Initializable {

    @FXML
    private Button btnAjouterReservationCp;

    @FXML
    private Button btnSupprimerReservationCp;

    @FXML
    private TableColumn<Camping,String> colAdresseReservationCp;

    @FXML
    private TableColumn<Camping,String> colNomReservationCp;

    @FXML
    private TableColumn<Camping,String> colNombreReservationCp;

    @FXML
    private TableColumn<Camping,String> colTelephoneReservationCp;

    @FXML
    private ComboBox<String> comboNomReservationCp;

    @FXML
    private TableView<Camping> tvReservationCp;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    Reservation r = new Reservation();
    Camping c = new Camping();
    ReservationCpService rcps = new ReservationCpService();

    private Camping camping = new Camping();
    private int idcamp;
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
        ObservableList nomsCampings = FXCollections.observableArrayList(rcps.getNomsCp());
        comboNomReservationCp.setItems(nomsCampings);
        comboNomReservationCp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            camping = rcps.retreiveCp(newValue);

            System.out.println(r);
             idcamp=camping.getIdCamping();
        });
        showReservationCp();
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterReservationCp)
            ajouterParticipationCp();
        if (event.getSource() == btnSupprimerReservationCp)
            supprimerParticipationCp();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {

    }

    @FXML
    private void ajouterParticipationCp(){
        try{
            Utilisateur u = new Utilisateur();
            UtilisateurService us = new UtilisateurService();
            u = (Utilisateur) UserSession.INSTANCE.get("user");

            r.setUtilisateur(u);
            r.setCamping(camping);
            rcps.ajouter(r);
            showReservationCp();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showReservationCp() {
        try{
            ObservableList<Camping> listReservationCp = rcps.afficher();
            colNomReservationCp.setCellValueFactory(new PropertyValueFactory<Camping, String>("nomCamping"));
            colAdresseReservationCp.setCellValueFactory(new PropertyValueFactory<Camping, String>("adresseCamping"));
            colTelephoneReservationCp.setCellValueFactory(new PropertyValueFactory<Camping, String>("telephoneCamping"));
            //colNombreParticipationEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("nombreParticipation"));
            tvReservationCp.setItems(listReservationCp);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void supprimerParticipationCp() {
        c = tvReservationCp.getSelectionModel().getSelectedItem();
        r.setCamping(c);
        rcps.supprimer(r);
        showReservationCp();
    }

}
