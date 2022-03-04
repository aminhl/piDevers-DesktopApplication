package Controllers;

import Entities.Hotel;
import Entities.Reservation;
import Entities.Utilisateur;
import Services.HotelService;
import Services.ReservationHotelService;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;



public class GestionHotelController implements Initializable {

    @FXML
    private Button bntActualiser;

    @FXML
    private Button btnAjoterHotel;

    @FXML
    private Button btnAjouterReservationHot;

    @FXML
    private Button btnModifierHotel;

    @FXML
    private Button btnSuppHotel;

    @FXML
    private Button btnSupprimerSupprimerHot;
    @FXML
    private Button btnSubmitHotel;

    @FXML
    private TableColumn<Hotel,String> colAdresseHotel;

    @FXML
    private TableColumn<Hotel,String> colEmailHotel;

    @FXML
    private TableColumn<Hotel,Integer> colEtoilesHotel;

    @FXML
    private TableColumn<Hotel,String> colNomHotel;

    @FXML
    private TableColumn<Hotel,Float> colPrixHotel;

    @FXML
    private TableColumn<Hotel,String> colReservationNomH;

    @FXML
    private TableColumn<Hotel,String> colReservationTelephoneH;

    @FXML
    private TableColumn<Hotel,String> colReservationadresseH;

    @FXML
    private TableColumn<Hotel,String> colTelHotel;

    @FXML
    private ComboBox<String> comboNomhot;

    @FXML
    private TableView<Hotel> tvHotel;

    @FXML
    private TableView<Hotel> tvReservationhot;
    @FXML
    private Circle idimg;
    @FXML
    private Label LbLogUser;

    @FXML
    private TextField tfAdresseHotel;

    @FXML
    private TextField tfEmailHotel;

    @FXML
    private TextField tfEtoileHotel;

    @FXML
    private TextField tfImageHotel;

    @FXML
    private TextField tfNomHotel;

    @FXML
    private TextField tfPrixHotel;

    @FXML
    private TextField tfTelHotel;

    @FXML
    private Tab tabRes;
    @FXML
    private Label randomHot;

    @FXML
    private Label randomPrix;

    @FXML
    private Label offreLabel;
    @FXML
    private Label nPrix ;
    @FXML
    private Label tauxS;
    @FXML
    private Button bntRemise;



    @FXML
    private TextField keyWordtextField;

    private Stage stage;
    private Scene scene;
   // private Parent root;
    private FXMLLoader fxmlLoader;
   // private int idHotel =0;
    Hotel h=new Hotel();
    Hotel myH=new Hotel();
    Reservation r = new Reservation();
    ReservationHotelService rs= new ReservationHotelService();
    HotelService hs = new HotelService();
    ObservableList<Hotel> listHotels = hs.afficher();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        LbLogUser.setText(u.getLoginUtilisateur());
        String path= null;
        try {
            path = us.LoadIMG(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        Image img= new Image(f.getAbsolutePath());
        idimg.setFill(new ImagePattern(img));
        showHotels();
        comboNomhot.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            myH = rs.getHotelForReservation(newValue);});
    }
    // Switch to Home Page
    public void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1087, 649);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
   //Hotel Section
   boolean test = false;
   public  void randomOff(){
       Random rand = new Random();
       if (test == false) {
           Double disc = (rand.nextInt(50)*0.01);
           Hotel randH=listHotels.get(rand.nextInt(listHotels.size()));
           Float f=randH.getPrixHotel();
           randomHot.setText(randH.getNomHotel());
           randomPrix.setText(f.toString()+"DT");
           Double soff=f-(f*disc);
           String amountDoubleFormated = new DecimalFormat("0.00").format(soff);
           offreLabel.setText(amountDoubleFormated+"DT");
           tauxS.setText("-"+String.valueOf(disc*100)+"%");
           test=true;

       }



   }

    public void showHotels(){

        ObservableList<Hotel> listH = hs.afficher();
        colNomHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nomHotel"));
        colAdresseHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresseHotel"));
        colEmailHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("emailHotel"));
        colPrixHotel.setCellValueFactory(new PropertyValueFactory<Hotel, Float>("prixHotel"));
        colEtoilesHotel.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("etoileHotel"));
        colTelHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("telephoneHotel"));
        tvHotel.setItems(listH);
        ObservableList nomHotel = FXCollections.observableArrayList(rs.getNomHotel());
        comboNomhot.setItems(nomHotel);
        //Fonction Recherche
            FilteredList<Hotel> filteredList = new FilteredList<>(listHotels,b->true);
            keyWordtextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(listHotels ->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyWord = newValue .toLowerCase();
                if(listHotels.getNomHotel().toLowerCase().indexOf(searchKeyWord)> -1){
                    return true;
                } else if (listHotels.getPrixHotel().toString().indexOf(searchKeyWord) > -1) {
                    return true;
                }else if (listHotels.getAdresseHotel().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                }else if (listHotels.getEtoileHotel().toString().indexOf(searchKeyWord) > -1) {
                    return true;
                }else
                    return  false;
            });
            });
        SortedList<Hotel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tvHotel.comparatorProperty());
        tvHotel.setItems(sortedList);

    }
    public void supprimerHotel() {
        h = tvHotel.getSelectionModel().getSelectedItem();
        hs.supprimer(h);
        tvHotel.refresh();
        showHotels();

    }
    public void modifierHotel(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/ModifierHotelView.fxml"));
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            Scene s = new Scene (anchor1);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imgs/elliot.png")));
            stage.getIcons().add(icon);
            stage.setScene(s);
            stage.setTitle("Modifier Hotel");
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void openAjouterHotelBullet(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AjouterHotel.fxml"));
        try {
            AnchorPane anchor = fxmlLoader.load();
            Stage stage = new Stage();
            Scene s = new Scene (anchor);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imgs/elliot.png")));
            stage.getIcons().add(icon);
            stage.setScene(s);
            stage.setTitle("Ajouter Hotel");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
// Reservation section
                public void showReservationHot() {
                    ObservableList<Hotel> listReservationhot = rs.afficher();
                    try{
                        colReservationNomH.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nomHotel"));
                        colReservationadresseH.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresseHotel"));
                        colReservationTelephoneH.setCellValueFactory(new PropertyValueFactory<Hotel, String>("telephoneHotel"));
                        //colNombreParticipationEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("nombreParticipation"));*/
                        tvReservationhot.setItems(listReservationhot);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

                    @FXML
                    void handleMouseAction() {
                        try{
                            Hotel hotel = tvReservationhot.getSelectionModel().getSelectedItem();
                            comboNomhot.setValue(hotel.getNomHotel());
                        }catch (Exception e){
                            System.out.println("");
                        }
                }
                    @FXML
                    private void ajouterReservationHot(){
                        try{
                            r.setHotel(myH);
                            rs.ajouter(r);
                            showReservationHot();

                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    public void supprimerReservationHot() {
                        h = tvReservationhot.getSelectionModel().getSelectedItem();
                        //System.out.println(e);
                        r.setHotel(h);
                        rs.supprimer(r);
                        showReservationHot();
                    }

}

