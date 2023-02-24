/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 21621
 */
public class ajouter1 extends Application {
    
   @Override
   public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ajouter1.fxml"));
             //Parent loader = FXMLLoader.load(getClass().getResource("afficherPersonnes.fxml"));

        Scene scene = new Scene(root,600,400);
 primaryStage.setTitle("ajouter note");
primaryStage.setScene(scene);
        primaryStage.show();

    }
   

    public static void main(String[] args) {
        launch(args);
    }

       
    
}
