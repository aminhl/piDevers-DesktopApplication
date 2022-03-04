package Controllers;

import Entities.Hotel;
import Services.HotelService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

public class AjouterHotelController {
    @FXML
    private Button btnSubmitHotel;

    @FXML
    private Button btnImport;

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    Hotel h = new Hotel();
    HotelService hs = new HotelService();
    GestionHotelController gh = new GestionHotelController();

    public void SubmitHotel(){
        try{
            h.setNomHotel(tfNomHotel.getText());
            h.setAdresseHotel(tfAdresseHotel.getText());
            h.setEmailHotel(tfEmailHotel.getText());
            h.setPrixHotel(Float.parseFloat(tfPrixHotel.getText()));
            h.setEtoileHotel(Integer.parseInt(tfEtoileHotel.getText()));
            h.setImageHotel(tfImageHotel.getText());
            h.setTelephoneHotel(tfTelHotel.getText());
            hs.ajouter(h);
            Stage stage = (Stage) btnSubmitHotel.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void  importImage(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();
        if (f!= null){
        String filename = f.getAbsolutePath();
        tfImageHotel.setText(filename);}




    }


    }



