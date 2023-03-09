package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Créer une nouvelle Timeline
        timeline = new Timeline(new KeyFrame(Duration.minutes(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("L'application se ferme car la souris est inactive.");
                System.exit(0); // Fermer l'application
            }
        }));

        // Définir la boucle d'animation en continu
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Ajouter un écouteur de souris pour redémarrer la Timeline
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                timeline.playFromStart(); // Redémarrer la Timeline à partir du début
            }
        });

        // Démarrer la Timeline
        timeline.play();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        timeline.stop(); // Arrêter la Timeline
    }

    public static void main(String[] args) {
        launch(args);
    }
}
