package Controllers;

import Entities.Evenement;
import Entities.Utilisateur;
import Services.EvenementService;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class    GestionEvenementController implements Initializable {

    @FXML
    private Button btnAjouterEvenement;

    @FXML
    private Button btnModifierEvenement;

    @FXML
    private Button btnSupprimerEvenement;

    @FXML
    private TableColumn<Evenement, String> colDateEvenement;

    @FXML
    private TableColumn<Evenement, String> colDescriptionEvenement;

    @FXML
    private TableColumn<Evenement, String> colLieuEvenement;

    @FXML
    private TableColumn<Evenement, String> colTitreEvenement;

    @FXML
    private TableColumn<Evenement, String> colTypeEvenement;

    @FXML
    private DatePicker datePickerEvenement;

    @FXML
    private HBox eventCardLayout;

    @FXML
    private TextField tfDescriptionEvenement;

    @FXML
    private TextField tfLieuEvenement;

    @FXML
    private TextField tfTitreEvenement;

    @FXML
    private TextField tfTypeEvenement;

    @FXML
    private TextField tfImageEvenement;

    @FXML
    private TableView<Evenement> tvEvenement;

    @FXML
    private ImageView eventHeaderImg;

    @FXML
    private Label eventHeaderName;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    @FXML
    private Button reserverBtn;

    @FXML
    private TextField searchtf;

    Evenement e = new Evenement();
    EvenementService es = new EvenementService();
    private List<Evenement> mesEvenements;

    public ObservableList data = FXCollections.observableArrayList();

    public void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1087, 649);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            Utilisateur u = new Utilisateur();
            UtilisateurService us = new UtilisateurService();
            u = (Utilisateur) UserSession.INSTANCE.get("user");
            eventHeaderName.setText(u.getLoginUtilisateur());
            String path= us.LoadIMG(u);
            File f = new File(path);
            Image img= new Image(f.getAbsolutePath());
            eventHeaderImg.setImage(img);
            setBtnStyles();
            //ObservableList<Evenement> listEvenement = es.afficher();
            data.addAll(es.afficher());
            colTitreEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titreEvenement"));
            colLieuEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuEvenement"));
            colTypeEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeEvenement"));
            colDescriptionEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("descriptionEvenement"));
            colDateEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateEvenement"));
            tvEvenement.setItems(data);
            //showEvenement();
            appendEvenementLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterEvenement)
            ajouterEvenement();
        if (event.getSource() == btnSupprimerEvenement)
            supprimerEvenement();
        if (event.getSource() == btnModifierEvenement)
            modifierEvenement();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        try{
            Evenement ev = tvEvenement.getSelectionModel().getSelectedItem();
            tfTitreEvenement.setText(ev.getTitreEvenement());
            tfLieuEvenement.setText(ev.getLieuEvenement());
            tfTypeEvenement.setText(ev.getTypeEvenement());
            tfDescriptionEvenement.setText(ev.getDescriptionEvenement());
            datePickerEvenement.setValue(ev.getDateEvenement().toLocalDate());
            tfImageEvenement.setText(ev.getImageEvenement());
        }catch (Exception e){
            System.out.println("");
        }
    }

     private void ajouterEvenement(){
        try{

            if (inputsControl()){
                e.setTitreEvenement(tfTitreEvenement.getText());
                e.setLieuEvenement(tfLieuEvenement.getText());
                e.setTypeEvenement(tfTypeEvenement.getText());
                e.setDescriptionEvenement(tfDescriptionEvenement.getText());
                e.setDateEvenement(Date.valueOf(datePickerEvenement.getValue()));
                e.setImageEvenement(tfImageEvenement.getText());
                es.ajouter(e);
                showEvenement();
                clearEvenementLayout();
                appendEvenementLayout();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Evenement Ajoutée Avec Succés!");
                alert.show();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
     }

    public void importImageEv(ActionEvent event) throws IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\PideversImgUploaded\\Event\\"+"Event"+x+".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            tfImageEvenement.setText(DBPath);

            int b=0;
            while(b!=-1){
                b=bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else  {
            System.out.println("error");
        }
    }
    public void showEvenement() {
        try{
            ObservableList<Evenement> listEvenement = es.afficher();
            colTitreEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titreEvenement"));
            colLieuEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuEvenement"));
            colTypeEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeEvenement"));
            colDescriptionEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("descriptionEvenement"));
            colDateEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateEvenement"));
            tvEvenement.setItems(listEvenement);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void supprimerEvenement() {
        e = tvEvenement.getSelectionModel().getSelectedItem();
        es.supprimer(e);
        showEvenement();
        clearEvenementLayout();
        appendEvenementLayout();
    }

    public void modifierEvenement(){
        e = tvEvenement.getSelectionModel().getSelectedItem();
        e.setTitreEvenement(tfTitreEvenement.getText());
        e.setLieuEvenement(tfLieuEvenement.getText());
        e.setTypeEvenement(tfTypeEvenement.getText());
        e.setDescriptionEvenement(tfDescriptionEvenement.getText());
        e.setDateEvenement(Date.valueOf(datePickerEvenement.getValue()));
        e.setImageEvenement(tfImageEvenement.getText());
        es.modifier(e);
        clearEvenementLayout();
        appendEvenementLayout();
        showEvenement();
    }

    public void appendEvenementLayout(){
        try{
            mesEvenements = es.afficher();
            for (Evenement ev : mesEvenements) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setDataEvenement(ev);
                eventCardLayout.getChildren().add(cardBox);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clearEvenementLayout(){
        eventCardLayout.getChildren().clear();
    }

    public void openReservationEvBullet(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/GestionParticipationEv.fxml"));
            try {
                AnchorPane anchor = fxmlLoader.load();
                Stage stage = new Stage();
                Scene s = new Scene (anchor);
                Image icon = new Image(getClass().getResourceAsStream("/imgs/elliot.png"));
                stage.getIcons().add(icon);
                stage.setScene(s);
                stage.setResizable(false);
                stage.sizeToScene();
                stage.setTitle("Reservation Evenement");
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public boolean inputsControl(){
        if (tfTitreEvenement.getText().trim().isEmpty() ||
                tfLieuEvenement.getText().trim().isEmpty() ||
                tfTypeEvenement.getText().trim().isEmpty() ||
                tfDescriptionEvenement.getText().trim().isEmpty() ||
                datePickerEvenement.getValue().equals(null)
                ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Veuillez Remplir Les Champs");
            alert.show();
            return false;
        }
        return true;
    }

    public void setBtnStyles(){
        btnAjouterEvenement.setTextFill(Color.WHITE);
        btnModifierEvenement.setTextFill(Color.WHITE);
        btnSupprimerEvenement.setTextFill(Color.WHITE);
        reserverBtn.setTextFill(Color.WHITE);
        tfTitreEvenement.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfLieuEvenement.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfTypeEvenement.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfDescriptionEvenement.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        datePickerEvenement.setStyle("-fx-control-inner-background: #131022;");
        tfImageEvenement.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
    }

    @FXML
    private void filterev(KeyEvent event) throws SQLException {
        data.clear();
        data.addAll(es.afficher().stream().filter(
                (e)
                -> e.getTitreEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getLieuEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getTypeEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getDescriptionEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }

}
