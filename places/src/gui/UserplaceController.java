/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class UserplaceController implements Initializable {

    private Label client;
    private Label etat;
    private Label sujet;
    @FXML
    private Label idparking;
    @FXML
    private Label type;
    @FXML
    private Label nbplaces;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setUserplace(Places p) {
        idparking.setText(String.valueOf(p.getId_parking()) );

        nbplaces.setText(String.valueOf(p.getNombres_place()) );
        type.setText(p.getType());
    }

    void set_row(Places get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
