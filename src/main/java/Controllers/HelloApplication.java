package Controllers;

import Entities.*;
import Services.*;
import Tools.MaConnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Views/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        MaConnexion mc = MaConnexion.getInstance();
        stage.setTitle("Hello Pidevers !");
        stage.setScene(scene);
        stage.show();
        // Event
        Evenement e  = new Evenement(1,"Titre","Lieu","Type",
                "Desc","2022-02,11","ImageEV.jpg");
        ReservationEvService rev = new ReservationEvService();
        Reservation r = new Reservation();
        r.setIdEvent(e.getIdEvenement());
        //rev.ajouter(r);

        // Camp

        Camping c = new Camping(2,"camp1","camp@@","aaa","54125488");
        CampingService cs = new CampingService();
        ReservationCpService rcp  = new ReservationCpService();
        Reservation rc = new Reservation();
        rc.setIdCamp(c.getIdCamping());
        /* cs.ajouter(c);
        rcp.ajouter(rc); */
        System.out.println(cs.afficher());

        // Randonne && Musueum
        Randonnee r1 = new Randonnee(8,"ab","cd",new java.util.Date(),new java.util.Date(),new java.util.Date(),"ef");
        Museum museum = new Museum(2,"null","encryptedImage",561134,5,new java.util.Date(),new java.util.Date());

        Randonnee rr = new Randonnee(10,"beja","kef",new java.util.Date(),new java.util.Date(),new java.util.Date(),"URL");
        RandonneeService randonnéeServices= new RandonneeService();
        MuseumServices museumServices = new MuseumServices();
        museumServices.ajouter(museum);
        System.out.println(museumServices.afficher());
        // museumServices.supprimer(museum);
        // museumServices.modifier(museum);
        // randonnéeServices.ajouter(rr);
        // randonnéeServices.modifier(r);
        System.out.println(randonnéeServices.afficher());
        //randonnéeServices.supprimer(r);
    }

    public static void main(String[] args) {
        launch();
    }
}