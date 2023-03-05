package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;

public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField emailField;

    private LoginManager loginManager;
    @FXML
    private AnchorPane forgotPasswordAnchorPane;
    @FXML
    private Button resetPasswordButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginManager = new LoginManager();

    }

    @FXML
    void resetPassword(ActionEvent event) throws MessagingException {
        String email = emailField.getText();

        // Vérifier si l'e-mail est valide
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Adresse e-mail invalide");
            alert.showAndWait();
            return;
        }

        // Vérifier si l'e-mail existe dans la base de données
        boolean emailExists = loginManager.checkEmailExists(email);

        if (emailExists) {
            // Générer un nouveau mot de passe aléatoire
            String newPassword = loginManager.generateRandomPassword();

            // Mettre à jour le mot de passe dans la base de données
            boolean passwordUpdated = loginManager.updatePassword(email, newPassword);

            if (passwordUpdated) {
                // Envoyer un e-mail avec le nouveau mot de passe
                // Code à compléter avec la logique d'envoi d'e-mail
                // ...
                            EmailManager.sendEmail(email,"mot de passe recuperé", newPassword);

                // Afficher un message à l'utilisateur pour lui indiquer que l'e-mail a été envoyé
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Réinitialisation de mot de passe");
                alert.setHeaderText("E-mail envoyé");
                alert.setContentText("Un e-mail avec des instructions pour réinitialiser votre mot de passe a été envoyé à " + email);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la réinitialisation du mot de passe");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Adresse e-mail non reconnue");
            alert.setContentText("Aucun compte utilisateur n'est associé à l'adresse e-mail " + email);
            alert.showAndWait();
        }
    }

    @FXML
    private void sms(ActionEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SendSMS.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
