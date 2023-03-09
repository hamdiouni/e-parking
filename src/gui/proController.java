/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class proController implements Initializable {

    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutercompte(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    @FXML
    private void ajouterplace(MouseEvent event) {
    }

    @FXML
    private void afficherplace(MouseEvent event) {
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();

    // Afficher l'interface de connexion
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    Parent root = loader.load();
    Stage loginStage = new Stage();
    loginStage.setScene(new Scene(root));
    loginStage.show();
    }

    @FXML
    private void quiiter(MouseEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("au revoir.");
        alert.showAndWait();
    }
    
}
