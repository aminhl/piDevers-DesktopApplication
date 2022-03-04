package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GestionStatistiqueController  {

    @FXML
    private ImageView eventHeaderImg;

    @FXML
    private Label eventHeaderName;

    @FXML
    void switchToMainFront(ActionEvent event) {

    }
    /*
    public void dd(Stage prStage)
    {

        HBox hb = new HBox();
        Scene scene= new Scene(hb,500,500);
        CategoryAxis axis = new CategoryAxis();
        axis.setLabel("Date");
        NumberAxis yaaxis = new NumberAxis();
        yaaxis.setLabel("Nombre");
        LineChart<String,Number> lineChart = new LineChart<String,Number>(axis,yaaxis);
        XYChart.Series<String ,Number> data = new XYChart.Series<>();
        data.setName("XXXX");
        data.getData().add(new XYChart.Data<String,Number>("1000",18000));
        data.getData().add(new XYChart.Data<String,Number>("1100",19000));
        data.getData().add(new XYChart.Data<String,Number>("1200",21500));
        data.getData().add(new XYChart.Data<String,Number>("1300",1000));
        lineChart.getData().add(data);
        hb.getChildren().add(lineChart);
        prStage.setTitle("dd10");
        prStage.setScene(scene);
        prStage.show();
    }
*/

}
