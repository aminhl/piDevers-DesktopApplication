package Controllers;

import Entities.Camping;
import Entities.Utilisateur;
import Services.CampingService;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class GestionCampingController implements Initializable {
    @FXML
    private Button btnAjouterCamping;
    private final ObservableList<Camping> dataList = FXCollections.observableArrayList();
    @FXML
    private Button btnModifierCamping;
    @FXML
    private Button fetcher;

    @FXML
    private Button btnSupprimerCamping;

    @FXML
    private TableColumn<Camping, String> colAdresseCamping;

    @FXML
    private TableColumn<Camping, String> colNomCamping;

    @FXML
    private TableColumn<Camping, String> colTelephoneCamping;

    @FXML
    private TableColumn<Camping, Float> colRatingCamping;

    @FXML
    private TextField tfAdresseCamping;

    @FXML
     TextField tfImageCamping;

    @FXML
    private Label LbLogUser;


    @FXML
    private TextField tfNomCamping;

    @FXML
    private TextField tfTelephoneCamping;
    @FXML
    private Circle idimg;
    @FXML
    private TableView<Camping> tvCamping;

    @FXML
    private TextField tfDescriptionCamping;

    @FXML
    private TextField tfRatingCamping;

    @FXML
    private TextField tfSearchCamp;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    Camping c = new Camping();
    CampingService cs = new CampingService();
    ObservableList<Camping> listCamping = cs.afficher();
    Connection cnx = MaConnexion.getInstance().getCnx();

    public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToReservationCp(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionReservationCp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Reservation Camping");
        stage.setScene(scene);
        stage.show();
    }
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

        try {
            setBtnStyles();
            showCamping();
        }catch (Exception e ){
            e.printStackTrace();}
    }


    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterCamping)
            ajouterCamping();
        if (event.getSource() == btnSupprimerCamping)
            supprimerCamping();
        if (event.getSource() == btnModifierCamping)
            modifierCamping();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        try{
            Camping cm = tvCamping.getSelectionModel().getSelectedItem();
            tfNomCamping.setText(cm.getNomCamping());
            tfAdresseCamping.setText(cm.getAdresseCamping());
            tfTelephoneCamping.setText(cm.getTelephoneCamping());
            tfRatingCamping.setText(String.valueOf(cm.getRatingCamping()));
            tfDescriptionCamping.setText(cm.getDescriptionCamping());
        }catch (Exception e){
            System.out.println("");
        }
    }

    private void ajouterCamping(){
        try{
            c.setNomCamping(tfNomCamping.getText());
            c.setAdresseCamping(tfAdresseCamping.getText());
            c.setImageCamping(tfImageCamping.getText());
            c.setTelephoneCamping(tfTelephoneCamping.getText());
            c.setRatingCamping(Float.valueOf(tfRatingCamping.getText()));
            c.setDescriptionCamping(tfDescriptionCamping.getText());
            cs.ajouter(c);
            showCamping();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void browseimage(ActionEvent event) throws IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\PideversImgUploaded\\Camping\\" + "Camp" + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(DBPath);
            tfImageCamping.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else {
            System.out.println("error");

        }
    }

    public void showCamping() {
        try{

            colNomCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("nomCamping"));
            colAdresseCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("adresseCamping"));
            colTelephoneCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("telephoneCamping"));
            colRatingCamping.setCellValueFactory(new PropertyValueFactory<Camping, Float>("ratingCamping"));

            tvCamping.setItems(listCamping);
            dataList.addAll(listCamping);

            FilteredList<Camping> filteredData = new FilteredList<>(listCamping , b -> true);
            Camping c = new Camping();

            tfSearchCamp.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(camping -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (camping.getNomCamping().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.

                    } else if (String.valueOf(camping.getRatingCamping()).indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (String.valueOf(camping.getAdresseCamping()).indexOf(lowerCaseFilter)!=-1)
                        return true;

                    else
                        return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Camping> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            //  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tvCamping.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tvCamping.setItems(sortedData);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void supprimerCamping() {
        c = tvCamping.getSelectionModel().getSelectedItem();
        cs.supprimer(c);
        showCamping();
    }

    public void modifierCamping(){
        c = tvCamping.getSelectionModel().getSelectedItem();
        c.setNomCamping(tfNomCamping.getText());
        c.setAdresseCamping(tfAdresseCamping.getText());
        c.setTelephoneCamping(tfTelephoneCamping.getText());
        cs.modifier(c);
        showCamping();
    }



    public void handlefetching(ActionEvent actionEvent)  {
        try{
            c=tvCamping.getSelectionModel().getSelectedItem();
            float one= (float) (c.getRatingCamping()-0.5);
            float two = (float) (c.getRatingCamping()+0.5);
            PreparedStatement ps=cnx.prepareStatement("select * from camping where rating_camping between +'"+one+"'  And'"+two+"' ");
            ResultSet rs= ps.executeQuery();
            ObservableList<Camping> listCamping = FXCollections.observableArrayList();
            while (rs.next()){
                Camping camping = new Camping();
                camping.setIdCamping(rs.getInt("id_camping"));
                camping.setNomCamping(rs.getString("nom_camping"));
                camping.setAdresseCamping(rs.getString("adresse_camping"));
                camping.setImageCamping(rs.getString("image_camping"));
                camping.setTelephoneCamping(rs.getString("telephone_camping"));
                camping.setRatingCamping(rs.getFloat("rating_camping"));
                listCamping.add(camping);}
            colNomCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("nomCamping"));
            colAdresseCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("adresseCamping"));
            colTelephoneCamping.setCellValueFactory(new PropertyValueFactory<Camping, String>("telephoneCamping"));
            tvCamping.setItems(listCamping);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("");
        }
    }

    @FXML
    public void chercher(){

    }


    @FXML
    void test(ActionEvent event) throws IOException {

    }

    public void setBtnStyles(){
        btnAjouterCamping.setTextFill(Color.WHITE);
        btnModifierCamping.setTextFill(Color.WHITE);
        btnSupprimerCamping.setTextFill(Color.WHITE);

        tfImageCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfAdresseCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfNomCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfDescriptionCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfRatingCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfTelephoneCamping.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
        tfSearchCamp.setStyle("-fx-text-inner-color: #fff;-fx-background-color: #131022; -fx-border-color: #fff; -fx-border-radius: 10;");
    }


}