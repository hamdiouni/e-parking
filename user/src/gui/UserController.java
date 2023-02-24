/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.user;
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

    private Label client;
    private Label etat;
    private Label sujet;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setUser(user p) {
                 username.setText(p.getUsername());

        email.setText(p.getEmail());
        password.setText(p.getPassword());
    }
    
}
