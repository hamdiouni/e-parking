package gui;

import entities.Places;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class PlaceDispoUniteController {

    @FXML
    private Label id;

    @FXML
    private Label parking_id;

    @FXML
    private Label prix;

    @FXML
    void reserver(ActionEvent event) {
        try {
            FormReservController newWindowController = new FormReservController();
            newWindowController.setId_place(Integer.parseInt(id.getText()));
            newWindowController.openNewWindow();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void set(Places p){
        this.id.setText(Integer.toString(p.getId_places()));
        this.parking_id.setText(Integer.toString(p.getId_parking()));
        this.prix.setText(p.getType());

    }

}
