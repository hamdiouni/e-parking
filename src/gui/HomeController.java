/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class HomeController implements Initializable {

    @FXML
    private Circle c1;
    @FXML
    private Button enter;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enter(ActionEvent event) {
        setRotate(c1,true,360,10);
                setRotate(c2,true,180,18);
                        setRotate(c3,true,145,24);

    }
     public void setRotate(Circle c,boolean reverse ,int angle,int duration){
                 RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);

        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(javafx.util.Duration.seconds(0));
         rt.setRate(3);
        rt.setCycleCount(10);
        rt.play();
    }

    @FXML
    private void note(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter1.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
