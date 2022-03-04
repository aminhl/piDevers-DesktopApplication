package Controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class CardController {
    @FXML
    private HBox BoxEvenement;

    @FXML
    private Label LieuEvenement;

    @FXML
    private Label TitreEvenement;

    @FXML
    private ImageView ImageEvenement;

    private String [] colors = {"b9e5ff", "bdb2fe", "fb9aa8", "ff5056"};

    public void setDataEvenement(Evenement evenement){
        TitreEvenement.setText(evenement.getTitreEvenement());
        LieuEvenement.setText(evenement.getLieuEvenement());
        String pathImageEv = evenement.getImageEvenement();
        File f = new File(pathImageEv);
        Image image = new Image(f.getAbsolutePath());
        ImageEvenement.setImage(image);
    }
}
