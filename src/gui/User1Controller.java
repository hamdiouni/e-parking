/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.abonnement;
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
public class User1Controller implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label tarif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setUser1(abonnement p) {
                 nom.setText(p.getNom());

        tarif.setText(String.valueOf(p.getTarif()) + "dt");
    }
}
