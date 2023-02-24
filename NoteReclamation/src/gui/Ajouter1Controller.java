/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.note;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.NoteService;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Ajouter1Controller implements Initializable {
    NoteService ps = new NoteService() ;

    @FXML
    private TextField messagetf;
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
    
    
     

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         LocalDate selectedDate = datetf.getValue();
         java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
        
          
       String nom=nomclienttf.getText();
      int rate=Integer.parseInt(ratetf.getText());
        String message=messagetf.getText();
                  note p = new note(nom,message,datee,rate);

        if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("Note n'est pas valide.");
    }

            // Valide l'objet User avant de le modifier
      /* try {
            //reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    
    } 
     private void reset() {
        nomclienttf.setText("");
        ratetf.setText("");
        messagetf.setText("");
        
       
    }

    @FXML
    private void supprimer(ActionEvent event) {
        note p = new note();
       
        p.setId_note(Integer.parseInt(idtf.getText()));
        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
          LocalDate selectedDate = datetf.getValue();
         java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
        
          
       String nom=nomclienttf.getText();
      int rate=Integer.parseInt(ratetf.getText());
        String message=messagetf.getText();
                  note p = new note(nom,message,datee,rate);

        System.out.println(p);
                p.setId_note(Integer.parseInt(idtf.getText()));

                    ps.modifier(p);
    }

    @FXML
    private void afficher(ActionEvent event) {
         try {
            //navigation
                        Parent loader = FXMLLoader.load(getClass().getResource("afficher2.fxml"));
            nomclienttf.getScene().setRoot(loader);

            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean isUserValid(note note) {
        if (note.getNom_client()== null || note.getNom_client().isEmpty()) {
            return false;
        }
        
        
        return !(note.getMessage()== null || note.getMessage().isEmpty());
    }

    }
        
        
        
        
        
        
        
        
        
        
        
    
    

