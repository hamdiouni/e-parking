package gui;
import entities.Places;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.PlacesService;

public class PlaceDispoController implements Initializable{

    PlacesService ps = new PlacesService();
    @FXML
    private GridPane affichagetf;

     public void load(){
        try {
            //BADEL PS.recuperer b ps.getAvalailablePlaces()
            List<Places> places = ps.recuperer();
            affichagetf.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < places.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("placedispounite.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                PlaceDispoUniteController  controller = loader.getController();
                controller.set(places.get(i));

                affichagetf.add(pane, column, row);
                row++;
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }      
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }

    
}
