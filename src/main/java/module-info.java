module pidevers.tourismapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires commons.validator;
    requires javax.mail.api;
    requires itextpdf;
    requires virtualizedfx;
    requires MaterialFX;
    requires org.slf4j.simple;
    requires twilio;
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.base;

    exports Controllers;
    exports Entities;
    opens Controllers to javafx.fxml;
    opens Entities to javafx.fxml;

}