package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField roleNameField;

    private LoginManager loginManager;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginManager = new LoginManager();
     
    }

    @FXML
void connexion(ActionEvent event) {
    String username = usernameField.getText();
    String password = passwordField.getText();

    // Vérifier si les informations de connexion sont valides
    boolean isAuthenticated = loginManager.authenticateUser(username, password);

    if (isAuthenticated) {
        try {
            // Ouvrir la fenêtre correspondante au rôle sélectionné
            FXMLLoader loader;
            Parent root;
            String roleName = roleNameField.getText();
            if ("admin".equals(roleName)) {
                loader = new FXMLLoader(getClass().getResource("home.fxml"));
                root = loader.load();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("welcome admin");
                alert.setHeaderText("welcome admin.");
                                alert.showAndWait();

            } else if ("proprietaire".equals(roleName)) {
                loader = new FXMLLoader(getClass().getResource("proprietaire.fxml"));
                root = loader.load();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("welcome proprietaire");
                alert.setHeaderText("welcome proprietaire.");
                                alert.showAndWait();

            } else if ("client".equals(roleName)) {
                loader = new FXMLLoader(getClass().getResource("client.fxml"));
                root = loader.load();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("welcome client");
                alert.setHeaderText("welcome client.");
                                alert.showAndWait();

            } else {
                // Afficher un message d'erreur si le rôle est invalide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText("Rôle incorrect.");
                alert.showAndWait();
                return;
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Enregistrer la connexion dans un fichier texte
            String filename = "log.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String dateTimeString = now.format(formatter);
                String logMessage = username + " s'est connecté le " + dateTimeString;
                writer.write(logMessage);
                writer.newLine();
                writer.flush(); // Force l'écriture immédiate des données dans le fichier
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connexion réussie");
            alert.setHeaderText("Vous êtes connecté");
            alert.showAndWait();

            // Fermer la fenêtre de connexion
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

        } catch (IOException ex) {
            // Gérer les erreurs d'ouverture de la fenêtre principale
            ex.printStackTrace();
        }
    } else {
        // Afficher un message d'erreur si les informations de connexion sont invalides
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Nom d'utilisateur ou mot de passe incorrect.");
        alert.showAndWait();
    }
}

    @FXML
    private void mdpoublie(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }



    @FXML
    private void activer(ActionEvent event) {
    URL resource = getClass().getResource("/gui/1.mp3");
    if (resource != null) {
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    // utilisation d'une expression lambda pour ajouter un événement au bouton toggleSound
    ToggleButton toggleSound = new ToggleButton("Activer/Désactiver le son");
    toggleSound.setOnAction(e -> {
        if (mediaPlayer != null && mediaPlayer.isMute()) {
            mediaPlayer.setMute(false);
            System.out.println("Son activé");
        }
    });

    // ajouter le bouton toggleSound au conteneur parent approprié (par exemple, VBox)
    // ...
}

@FXML
private void desactiver(ActionEvent event) {
    if (mediaPlayer != null && !mediaPlayer.isMute()) {
        mediaPlayer.setMute(true);
        System.out.println("Son désactivé");
    }
    
    // utilisation d'une expression lambda pour ajouter un événement au bouton muteSound
    Button muteSound = new Button("Désactiver le son");
    muteSound.setOnAction(e -> {
        if (mediaPlayer != null && !mediaPlayer.isMute()) {
            mediaPlayer.setMute(true);
            System.out.println("Son désactivé");
        }
    });

    // ajouter le bouton muteSound au conteneur parent approprié (par exemple, VBox)
    // ...
}











}  // Rien à faire ici car la sélection
