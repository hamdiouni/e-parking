/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.user;
import java.io.IOException;
import services.userSrevice;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class AjouterController implements Initializable {

    
     private Button ajouterBtn;
    userSrevice ps = new userSrevice() {
         @Override
         public List<user> rechercher(user t) throws SQLException {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public List<user> trierParTarif() throws SQLException {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         
     };
    @FXML
    private TextField idtf;
    private TextField nomclienttf;
    private TextField etattf;
    private TextField sujettf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField passwordtf;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    

      public boolean isUserValid(user user) {
        if (user.getUsername()== null || user.getUsername().isEmpty()) {
            return false;
        }
        if (user.getPassword()== null || user.getPassword().isEmpty()) {
            return false;
        }
        return !(user.getEmail()== null || user.getEmail().isEmpty());
    }

    @FXML
    private void ajouter(ActionEvent event) {
        user p = new user();
        p.setUsername(username.getText());
        p.setEmail(emailtf.getText());
        p.setPassword(passwordtf.getText());
       if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("ajout avec succes.");
        alert.showAndWait();
    } else {
        System.out.println("L'objet User n'est pas valide.");
        
    Alert alert = new Alert(Alert.AlertType.ERROR, "L'objet User n'est pas valide.", ButtonType.OK);
        alert.showAndWait();
    }
    }
 private void reset() {
        username.setText("");
        emailtf.setText("");
        passwordtf.setText("");
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficher.fxml"));
            username.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("affichage avec success.");
        alert.showAndWait();
    }

    @FXML
    private void modifier(ActionEvent event) {
       user p = new user();
                        p.setId_user(Integer.parseInt(idtf.getText()));

        p.setUsername(username.getText());
        p.setEmail(emailtf.getText());
        p.setPassword(passwordtf.getText());
       if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.modifier(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Cet utilisateur modifie.");
        alert.showAndWait();
    } else {
        System.out.println("L'objet User n'est pas valide.");
       Alert alert = new Alert(Alert.AlertType.ERROR, "L'objet User n'est pas valide.", ButtonType.OK);
        alert.showAndWait();
    }
       
    }

    @FXML
    private void supprimer(ActionEvent event) {
        user p = new user();
       
        p.setEmail(emailtf.getText());
        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("user supprimer.");
        alert.showAndWait();
    }
     @FXML
      private void handleQuitter(ActionEvent event) {
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("au revoir.");
        alert.showAndWait();
    }


    
}
