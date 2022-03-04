package Controllers;

import Entities.Article;
import Entities.Commentaire;
import Entities.Utilisateur;
import Services.*;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GestionBlogController implements Initializable {

    @FXML
    private TableColumn<Article, Integer> col_cat;

    @FXML
    private TableColumn<Article, String> col_date;

    @FXML
    private TableColumn<Article, String> col_desc;

    @FXML
    private TableColumn<Article, Integer> col_id;

    @FXML
    private TableColumn<Article, String> col_titre;

    @FXML
    private TableColumn<Article, Integer> col_us;

    @FXML
    private TextArea comment;

    @FXML
    private TextField desc;


    @FXML
    private ImageView eventHeaderImg;

    @FXML
    private Label eventHeaderName;

    @FXML
    private ComboBox<String> idC;

    @FXML
    private TextField idU;



    @FXML
    private Button imageBlogBtn;

    @FXML
    private TextField img;


    @FXML
    private Label warning;
    @FXML
    private Button show;

    @FXML
    private Button submit;

    @FXML
    private Button submit1;

    @FXML
    private Button submit11;

    @FXML
    private TableView<Article> table_article;
    @FXML
    private TextField id;
    @FXML
    private Circle idimg1;
    @FXML
    private Label LbLogUser;
    @FXML
    private TextField titre;
    @FXML
    private ToggleButton like;
    @FXML
    private ToggleButton dislike;
    @FXML
    private ToggleGroup groupLike;
    @FXML
            private  Label commentLabel;

    Connection cnx = MaConnexion.getInstance().getCnx();
    Article a = new Article();
    ArticleService ar = new ArticleService();
    Utilisateur u = new Utilisateur();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        UtilisateurService us =new UtilisateurService();
        LbLogUser.setText(u.getLoginUtilisateur());
        String path= null;
        try {
            path = us.LoadIMG(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        Image img= new Image(f.getAbsolutePath());
        idimg1.setFill(new ImagePattern(img));
        BadWords.loadConfigs();
        ObservableList<String> ComboData = FXCollections.observableArrayList();
        String query = "SELECT nom_categorie FROM categorie";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                ComboData.add(rs.getString("nom_categorie"));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        idC.setItems(ComboData);

        afficher();
        System.out.println(u.getIdUtilisateur());
    }



    private String getNomCat(int idCat){
        String queryCat = "SELECT nom_categorie FROM categorie WHERE id_categorie = '" + idCat + "'";
        String nomCategorie = "";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(queryCat);
            while (rs.next()) {
                nomCategorie = (rs.getString("nom_categorie"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return nomCategorie;
    }
    @FXML
    protected void addA() {
        u = (Utilisateur) UserSession.INSTANCE.get("user");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String queryCat = "SELECT id_categorie FROM categorie WHERE nom_categorie = '" + idC.getValue() + "'";
        int idCategorie = 0;
        try {
            System.out.println(idC.getValue());

            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(queryCat);
            while (rs.next()) {
                idCategorie = (rs.getInt("id_categorie"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        a.setIdCategorie(idCategorie);

        a.setTitreArticle(titre.getText());
        if (BadWords.badWordslength(desc.getText())>0){
            warning.setText("Vous avez tapez " + String.valueOf(BadWords.badWordslength(desc.getText())+" mot(s) grossier(s) lors de l'insertion de l'article ! \n priére de les enlever !"));
            commentLabel.setText("");

        }else{
            a.setDescriptionArticle(desc.getText());
            warning.setText("");

        }
        a.setImageArticle(img.getText());
        a.setDateArticle(dtf.format(now));
        a.setIdUtilisateur(u.getIdUtilisateur());
        ar.ajouter(a);
        afficher();
    }
    @FXML
    void addC(ActionEvent event) {
        u = (Utilisateur) UserSession.INSTANCE.get("user");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Commentaire c = new Commentaire();
        if (BadWords.badWordslength(comment.getText())>0){
            warning.setText("Vous avez tapez " + String.valueOf(BadWords.badWordslength(comment.getText())+" mot(s) grossier(s) lors de l'inesertion du commentaire ! \n priére de les enlever !"));
        }else{
            c.setContenuCommentaire(comment.getText());
            warning.setText("");

        }
        c.setDateCommentaire(dtf.format(now));
        c.setEtatCommentaire(1);
        c.setIdArticle(Integer.parseInt(id.getText()));
        c.setIdUtilisateur(u.getIdUtilisateur());
        CommentaireService cs = new CommentaireService();
        cs.ajouter(c);
        commentLabel.setText("Commentaire ajouté !");
        warning.setText("");
        comment.setText("");
    }
    protected void afficher(){
        ObservableList<Article> list = ar.afficher();



        col_id.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titreArticle"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("descriptionArticle"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateArticle"));
        col_cat.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        col_us.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));


        table_article.setItems(list);
    }
    @FXML
    void delete(ActionEvent event) {
        a.setIdArticle(Integer.parseInt(id.getText()));
        ar.supprimer(a);
        afficher();
    }
    @FXML
    void handleMouseAction(MouseEvent event) {
        Article article = table_article.getSelectionModel().getSelectedItem();
        String nomCategorie = getNomCat(article.getIdCategorie());

        id.setText(""+article.getIdArticle());
        titre.setText(""+article.getTitreArticle());
        desc.setText(""+article.getDescriptionArticle());
        //img.setText(""+article.getImageArticle());
        idC.setValue(nomCategorie);
        idU.setText(""+article.getIdUtilisateur());
        if(checkLikeDislikeExists(article.getIdUtilisateur(), article.getIdArticle())){
            if(checkLikeDislike(article.getIdUtilisateur(), article.getIdArticle())){
                like.setSelected(true);
            }else if(!checkLikeDislike(article.getIdUtilisateur(), article.getIdArticle())){
                dislike.setSelected(true);

            }
        }else{
            like.setSelected(false);
            dislike.setSelected(false);
        }


        System.out.println(id.getText());
    }
    @FXML
    void importImageBlog(ActionEvent event) {
    }
    @FXML
    void likedislike(ActionEvent event) {
        u = (Utilisateur) UserSession.INSTANCE.get("user");

        if(event.getSource()== like){
            if (checkLikeDislikeExists(u.getIdUtilisateur(),Integer.parseInt(id.getText()))){
                String query="UPDATE `likedislike` SET `islike` = '1' WHERE id_utilisateur= '"+u.getIdUtilisateur()+"' AND id_article='"+Integer.parseInt(id.getText())+"'";
                try{
                    Statement ste = cnx.createStatement();
                    ste.executeUpdate(query);
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }else{
                String query = "INSERT INTO likedislike(id_utilisateur,id_article,islike)" + "VALUES(?,?,?)";
                try{
                    PreparedStatement ste = cnx.prepareStatement(query);
                    ste.setInt(1,u.getIdUtilisateur());
                    ste.setInt(2,Integer.parseInt( id.getText()));
                    ste.setBoolean(3,true);


                    ste.executeUpdate();

                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }

        }
        if(event.getSource()== dislike){

            if (checkLikeDislikeExists(u.getIdUtilisateur(),Integer.parseInt(id.getText()))) {
                String query = "UPDATE `likedislike` SET `islike` = '0' WHERE id_utilisateur = '" + u.getIdUtilisateur() + "' AND id_article= '" + Integer.parseInt(id.getText()) + "'";
                try {
                    Statement ste = cnx.createStatement();
                    ste.executeUpdate(query);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            else{
                String query = "INSERT INTO likedislike(id_utilisateur,id_article,islike)" + "VALUES(?,?,?)";
                try{
                    PreparedStatement ste = cnx.prepareStatement(query);
                    ste.setInt(1,u.getIdUtilisateur());
                    ste.setInt(2,Integer.parseInt(id.getText()));
                    ste.setBoolean(3,false);


                    ste.executeUpdate();

                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        if((!like.isSelected()) &&(!dislike.isSelected())){
            String query = "Delete from likedislike where id_utilisateur = '" + u.getIdUtilisateur() + "' AND id_article= '" + Integer.parseInt(id.getText()) + "'";
            try{
                Statement ste = cnx.createStatement();
                ste.executeUpdate(query);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public boolean checkLikeDislike(int idUtilisateur,int idArticle) {
        String query = "Select isLike from likedislike where id_utilisateur='" + idUtilisateur + "'and id_article='" + idArticle + "'";
        boolean check = false;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                check = rs.getBoolean("isLike");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    @FXML
    void switchScene(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) show.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/views/GestionCommentaire.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToMainFront(ActionEvent event) {

    }
    public boolean checkLikeDislikeExists(int idUtilisateur,int idArticle) {
        String sql = "Select * from likedislike where id_utilisateur = ? And id_article = ? ;";
        boolean check = false;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            ps.setInt(2,idArticle);
            ResultSet rs = ps.executeQuery();
            check = rs.next();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return check;

    }
    @FXML
    void update(ActionEvent event) {
        String queryCat = "SELECT id_categorie FROM categorie WHERE nom_categorie = '" + idC.getValue() + "'";
        int idCategorie = 0;
        try {
            System.out.println(idC.getValue());

            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(queryCat);
            while (rs.next()) {
                idCategorie = (rs.getInt("id_categorie"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        a.setIdArticle(Integer.parseInt(id.getText()));
        a.setCategorie(a.setIdCategorie(idCategorie));
        a.setTitreArticle(titre.getText());
        a.setDescriptionArticle(desc.getText());
        a.setUtilisateur(a.setIdUtilisateur(Integer.parseInt(idU.getText())));
        ar.modifier(a);


        afficher();
    }


}
