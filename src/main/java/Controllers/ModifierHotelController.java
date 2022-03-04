package Controllers;

import Entities.Hotel;
import Services.HotelService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierHotelController implements Initializable {

    @FXML
    private Button btnModifierHotel;

    @FXML
    private TableColumn<Hotel, String> colAdresseHotel;

    @FXML
    private TableColumn<Hotel, String> colEmailHotel;

    @FXML
    private TableColumn<Hotel, Integer> colEtoilesHotel;

    @FXML
    private TableColumn<Hotel, String> colNomHotel;

    @FXML
    private TableColumn<Hotel, Float> colPrixHotel;

    @FXML
    private TableColumn<Hotel, String> colTelHotel;

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
    private TableView<Hotel> tvHotel;
    @FXML
    private Button btnimportImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    showHotels();
    }
    Hotel h = new Hotel();
    HotelService hs = new HotelService();
    public void modifierHotel(){
        try{
            h = tvHotel.getSelectionModel().getSelectedItem();
            h.setNomHotel(tfNomHotel.getText());
            h.setAdresseHotel(tfAdresseHotel.getText());
            h.setEmailHotel(tfEmailHotel.getText());
            h.setPrixHotel(Float.parseFloat(tfPrixHotel.getText()));
            h.setEtoileHotel(Integer.parseInt(tfEtoileHotel.getText()));
            h.setImageHotel(tfImageHotel.getText());
            h.setTelephoneHotel(tfTelHotel.getText());
            hs.modifier(h);
            showHotels();
            Stage stage = (Stage) btnModifierHotel.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showHotels() {
        ObservableList<Hotel> listHotels = hs.afficher();
        colNomHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nomHotel"));
        colAdresseHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresseHotel"));
        colEmailHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("emailHotel"));
        colPrixHotel.setCellValueFactory(new PropertyValueFactory<Hotel, Float>("prixHotel"));
        colEtoilesHotel.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("etoileHotel"));
        colTelHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("telephoneHotel"));
        tvHotel.setItems(listHotels);
    }
    public void handleMouseAction() {
        try{
            Hotel ev = tvHotel.getSelectionModel().getSelectedItem();
            tfAdresseHotel.setText(ev.getAdresseHotel());
            tfEmailHotel.setText(ev.getEmailHotel());
            tfNomHotel.setText(ev.getNomHotel());
            tfPrixHotel.setText(Float.parseFloat(" "+ev.getPrixHotel())+"");
            tfTelHotel.setText(ev.getTelephoneHotel());
            tfEtoileHotel.setText(Integer.parseInt(" "+ev.getEtoileHotel())+"");
            tfImageHotel.setText(ev.getImageHotel());
        }catch (Exception e){
            System.out.println("");
        }
    }
    public void  importImage(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if (f != null){
        String filename = f.getAbsolutePath();
        tfImageHotel.setText(filename);}


    }

}
