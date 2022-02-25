module pidevers.tourismapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    /*opens pidevers.Controllers to javafx.fxml;
    exports pidevers.Controllers;*/
    exports Controllers;
    opens Controllers to javafx.fxml;
}