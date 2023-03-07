package gui;


import entities.Places;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;





/**
 * FXML Controller class
 *
 * @author 21621
 */
public class PlaceController implements Initializable  {

    @FXML
    private Label id;

    @FXML
    private Label parking_id;

    @FXML
    private Label type;

    public void set(Places p){
        this.id.setText(Integer.toString(p.getId_places()));
        this.parking_id.setText(Integer.toString(p.getId_parking()));
        this.type.setText(p.getType());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
