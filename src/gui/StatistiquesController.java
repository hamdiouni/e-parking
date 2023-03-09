package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import utils.MyDB;

public class StatistiquesController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = MyDB.getInstance().getCnx();
        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count, categorieVoiture FROM vehicule GROUP BY categorieVoiture")) {
            while (resultSet.next()) {
                String category = resultSet.getString("categorieVoiture");
                int count = resultSet.getInt("count");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(category, count));
                barChart.getData().add(series);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
