/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.note;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.NoteService;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Ajouter1Controller implements Initializable {
    NoteService ps = new NoteService() ;

    @FXML
    private TextField messagetf;
    @FXML
    private TextField nomclienttf;
    @FXML
    private Button button;
    @FXML
    private TextField ratetf;
    @FXML
    private DatePicker datetf;
    @FXML
    private TextField idtf;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
    
    
     

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         LocalDate selectedDate = datetf.getValue();
         java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
        
          
       String nom=nomclienttf.getText();
      int rate=Integer.parseInt(ratetf.getText());
        String message=messagetf.getText();
                  note p = new note(nom,message,datee,rate);

        if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("Note n'est pas valide.");
    }

            // Valide l'objet User avant de le modifier
      /* try {
            //reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    
    } 
     private void reset() {
        nomclienttf.setText("");
        ratetf.setText("");
        messagetf.setText("");
        
       
    }

    @FXML
    private void supprimer(ActionEvent event) {
        note p = new note();
       
        p.setId_note(Integer.parseInt(idtf.getText()));
        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
          LocalDate selectedDate = datetf.getValue();
         java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
        
          
       String nom=nomclienttf.getText();
      int rate=Integer.parseInt(ratetf.getText());
        String message=messagetf.getText();
                  note p = new note(nom,message,datee,rate);

        System.out.println(p);
                p.setId_note(Integer.parseInt(idtf.getText()));

                    ps.modifier(p);
    }

    @FXML
    private void afficher(ActionEvent event) {
         try {
            //navigation
                        Parent loader = FXMLLoader.load(getClass().getResource("afficher2.fxml"));
            nomclienttf.getScene().setRoot(loader);

            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean isUserValid(note note) {
        if (note.getNom_client()== null || note.getNom_client().isEmpty()) {
            return false;
        }
        
        
        return !(note.getMessage()== null || note.getMessage().isEmpty());
    }

    @FXML
    private void envoyer(ActionEvent event) {
    
        // Récupération des informations du formulaire
        String from = fromField.getText();
        String to = toField.getText();
        String nom = nomclienttf.getText();
        String message = messagetf.getText();

        // Configuration des propriétés du serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Création de l'objet Session pour se connecter au serveur SMTP
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("fakhfakh4321@gmail.com", "tfuyobwcgvomohup");
            }
        });
        try {
            // Création de l'objet MimeMessage pour composer le message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setText(message);

            // Envoi du message
            Transport.send(mimeMessage);

            // Affichage d'une boîte de dialogue pour indiquer que le message a été envoyé
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message envoyé");
            alert.setHeaderText(null);
            alert.setContentText("Le message a été envoyé avec succès.");
            alert.showAndWait();

            // Fermeture de la fenêtre

        } catch (MessagingException e) {
            // Affichage d'une boîte de dialogue pour indiquer qu'une erreur s'est produite
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'envoi du message : " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
                      BarChart<String, Number> chart = loader.load();

    }

  

    }
        
        
        
        
        
        
        
        
        
        
        
    
    

