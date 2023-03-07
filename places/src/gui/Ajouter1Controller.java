/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.Reservation;
import entities.Places;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
/*public class Ajouter1Controller implements Initializable {
    ReservationService ps = new ReservationService() ;

    @FXML
    private TextArea messagetf;
    @FXML
    private TextField nomclienttf;
    @FXML
    private Button button;
    @FXML
    private TextField ratetf;
    @FXML
    private DatePicker datetf;
    @FXML
    private TextField idtf;

    /**
     * Initializes the controller class.
     */
    @Override
    /*public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public boolean isUserValid(Reservation note) {
        if (note.getNom_client()== null || note.getNom_client().isEmpty()) {
            return false;
        }
        if (note.getNom_client()== null || note.getNom_client().isEmpty()) {
            return false;
        }
        return !(note.getMessage()== null || note.getMessage().isEmpty());
    }
    
    
    
    private void AjouterNote(ActionEvent event) {
         LocalDate selectedDate = datetf.getValue();
         java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
        
          Reservation p = new Reservation();
          p.setDate(datee);
        p.setNom_client(nomclienttf.getText());
        p.setMessage(messagetf.getText());
        int rate=Integer.parseInt(ratetf.getText());
        p.setRate(rate);
            if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("L'objet User n'est pas valide.");
    }

      
    }
     private void reset() {
        nomclienttf.setText("");
        messagetf.setText("");
        ratetf.setText("");
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }
        
        
        
        
        
        
        
        
        
        
        
    }*/
    

