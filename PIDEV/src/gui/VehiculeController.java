/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vehicule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class VehiculeController implements Initializable {

    @FXML
    private Label marque;
    @FXML
    private Label modele;
    @FXML
    private Label couleur;
    @FXML
    private Label matricule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void setVehicule(Vehicule p) {
                 marque.setText(p.getMarque());

        modele.setText(p.getModele());
        couleur.setText(p.getCouleur());
        matricule.setText(p.getMatricule());
    }
    
}
