package Controllers;

import Entities.Commentaire;
import Entities.Utilisateur;
import Services.CommentaireService;
import Tools.MaConnexion;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;



public class CommentairesController implements Initializable{

    @FXML
    private TableView<Commentaire> table_commentaire;
    @FXML
    private TableColumn<Commentaire,Integer> col_id;
    @FXML
    private TableColumn<Commentaire,String>  col_contenu;
    @FXML
    private TableColumn<Commentaire,String>  col_date;
    @FXML
    private TableColumn<Commentaire, Integer>  col_article;
    @FXML
    private TableColumn<Commentaire, Integer>  col_utilisateur;

    @FXML
    private Button retour;

    @FXML
    private TextField id;
    @FXML
    private TextArea comment;


    Connection cnx = MaConnexion.getInstance().getCnx();




    Utilisateur yassin = new Utilisateur(1 ,"Khabthani" ,
            "Yassin" ,
            "yassin@gmail.com",
            "yassin123" ,
            "azerty123" ,
            "yassin.png" ,
            1 ,
            "52176848" ,
            "01 Rue El Bohtouri Ariana 2080");
    CommentaireService cs = new CommentaireService();
    //ObservableList<Commentaire> commentaireList = cs.afficher();
    Commentaire c = new Commentaire();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    afficherCommentaire();

    }
@FXML
public void switchToMainFront(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/Views/MainFront.fxml"));
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
    private void switchScene () throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) retour.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/views/Formulaire.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    protected void afficherCommentaire(){
        ObservableList<Commentaire> list = cs.afficher();

        col_id.setCellValueFactory(new PropertyValueFactory<>("idCommentaire"));
        col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenuCommentaire"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateCommentaire"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateCommentaire"));
        col_article.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        col_utilisateur.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));


        table_commentaire.setItems(list);
    }


    @FXML
    private void handleMouseAction(MouseEvent event) {
        Commentaire commentaire = table_commentaire.getSelectionModel().getSelectedItem();

        id.setText("" + commentaire.getIdCommentaire());
        comment.setText("" + commentaire.getContenuCommentaire());
    }
    @FXML
    private void update(){
        c.setContenuCommentaire(comment.getText());
        c.setIdCommentaire(Integer.parseInt(id.getText()));
        cs.modifier(c);
        afficherCommentaire();
    }
    @FXML
    private void delete(){

        c.setIdCommentaire(Integer.parseInt(id.getText()));
        cs.supprimer(c);
        afficherCommentaire();
    }
}
