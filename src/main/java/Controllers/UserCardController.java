package Controllers;

import Entities.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class UserCardController {
    @FXML
    private VBox Boxuser;
    @FXML
    private ImageView ImageUser;
    @FXML
    private Label LbLogUser;
    @FXML
    private Circle CircleImg;
    @FXML
    private Label idrole;
   // private String [] colors = {"b9e5ff", "bdb2fe", "fb9aa8", "ff5056"};
    public void setDataUser(Utilisateur user){
        LbLogUser.setText(user.getLoginUtilisateur());
        if (user.getRankUtilisateur()==1) {
            idrole.setText("Admin");
        }else
        {
            idrole.setText("Client");
        }
        String path = user.getImgUtilisateur();
        File file = new File(path);
       Image image = new Image(file.getAbsolutePath());
        CircleImg.setFill(new ImagePattern(image));
      //  CircleImg.setEffect(new DropShadow(+25d,0d,+2d,Color.DARKSEAGREEN));
    }
}
