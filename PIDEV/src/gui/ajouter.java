/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Firas
 */
public class ajouter extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("../gui/ajoutervehicule.fxml"));
             //Parent loader = FXMLLoader.load(getClass().getResource("afficherVoiture.fxml"));

        Scene scene = new Scene(root,600,400);
 primaryStage.setTitle("ajouter voiture");
primaryStage.setScene(scene);
        primaryStage.show();       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
