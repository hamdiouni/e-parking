package gui;

import entities.Places;
import entities.Reservation;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ReservationService;

public class FormReservController {

   
    ReservationService rs = new ReservationService();
    private int id_place;
    
    @FXML
    private TextField duree;

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }


    public void openNewWindow() throws IOException {
        // Load the FXML file for the new window
        Parent root = FXMLLoader.load(getClass().getResource("FormReserv.fxml"));

        // Create a new stage for the new window and set the scene
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Réserver une place");
        stage.show();
    }
    public void close(){
        
    }
    @FXML
    void soumettre(ActionEvent event) {
        Reservation r = new Reservation();
        System.out.println(this.id_place);
        r.setId_place(this.id_place);
        System.out.println(r.getId_place());
        r.setPrix(200f); 

        r.setDuree(duree.getText());
        try {
            rs.ajouter(r);
            close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
