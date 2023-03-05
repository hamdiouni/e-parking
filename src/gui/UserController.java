/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.reclamation;
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
public class UserController implements Initializable {

    @FXML
    private Label client;
    @FXML
    private Label etat;
    @FXML
    private Label sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setUser(reclamation p) {
                 client.setText(p.getNom_client());

        sujet.setText(p.getSujet());
        etat.setText(p.getEtat());
    }
     
    
}
