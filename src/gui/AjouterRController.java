/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.reclamation;
import java.io.IOException;
import services.ReclamationSrevice;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class AjouterRController implements Initializable {

    
     private Button ajouterBtn;
    ReclamationSrevice ps = new ReclamationSrevice() {};
    @FXML
    private TextField idtf;
    @FXML
    private TextField nomclienttf;
    @FXML
    private TextField etattf;
    @FXML
    private TextField sujettf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    

     public boolean isUserValid(reclamation reclamation) {
        if (reclamation.getNom_client() == null || reclamation.getNom_client().isEmpty()) {
            return false;
        }
        if (reclamation.getNom_client()== null || reclamation.getNom_client().isEmpty()) {
            return false;
        }
        return !(reclamation.getEtat()== null || reclamation.getEtat().isEmpty());
    }

    @FXML
    private void ajouter(ActionEvent event) {
        reclamation p = new reclamation();
        p.setNom_client(nomclienttf.getText());
        p.setEtat(etattf.getText());
        p.setSujet(sujettf.getText());
       if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("La  Reclamation n'est pas valide.");
    }
    }
 private void reset() {
        nomclienttf.setText("");
        etattf.setText("");
        sujettf.setText("");
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherR.fxml"));
            nomclienttf.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         reclamation p = new reclamation();
      p.setId_reclamtion(Integer.parseInt(idtf.getText()));
           p.setNom_client(nomclienttf.getText());
        p.setEtat(etattf.getText());
        p.setSujet(sujettf.getText());
         if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.modifier(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("L'objet User n'est pas valide.");
    }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        reclamation p = new reclamation();
       
        p.setId_reclamtion(Integer.parseInt(idtf.getText()));
        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    
}
