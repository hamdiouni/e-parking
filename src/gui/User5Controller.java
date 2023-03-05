/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.note;
import entities.reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class User5Controller implements Initializable {

    @FXML
    private Label client;
    @FXML
    private Label message;
    @FXML
    private Label date;
    @FXML
    private Label rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setUser(note p) {
                client.setText(p.getNom_client());
                message.setText(p.getMessage());
                                date.setText(String.valueOf(p.getDate()));

                rate.setText(String.valueOf(p.getRate()));
    
    }
}
