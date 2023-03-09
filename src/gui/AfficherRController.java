/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.note;
import entities.reclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ReclamationSrevice;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class AfficherRController implements Initializable {

    @FXML
    private GridPane grid;
    ReclamationSrevice ps = new ReclamationSrevice() {};
    @FXML
    private TextField nommtf;
  Connection cnx;
    @FXML
    private TextField messagetf;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<reclamation> reclamations = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < reclamations.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userR.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              userRController  controller = loader.getController();
                controller.setuser(reclamations.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }

    @FXML
    private void rechercher(ActionEvent event) throws IOException, SQLException {
       reclamation reclamation = new reclamation();
    reclamation.setNom_client(nommtf.getText());

            


    List<reclamation> reclamations = ps.rechercher(reclamation);
    grid.getChildren().clear(); // vider le GridPane
    
    int row = 0;
    int column = 0;
    for (int i = 0; i < reclamations.size(); i++) {
        //chargement dynamique d'une interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userR.fxml"));
        AnchorPane pane = loader.load();

        //passage de parametres
        userRController controller = loader.getController();
        controller.setuser(reclamations.get(i));

        grid.add(pane, column, row);
        column++;
        if (column > 1) {
            column = 0;
            row++;
        }
    }
    }

    @FXML
    private void tri(ActionEvent event) throws IOException {
    try {
        List<reclamation> reclamations = ps.trierParTarif();
        grid.getChildren().clear(); // vider le GridPane
        
        int row = 0;
        int column = 0;
        for (int i = 0; i < reclamations.size(); i++) {
            //chargement dynamique d'une interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userR.fxml"));
            AnchorPane pane = loader.load();

            //passage de parametres
            userRController controller = loader.getController();
            controller.setuser(reclamations.get(i));

            grid.add(pane, column, row);
            column++;
            if (column > 1) {
                column = 0;
                row++;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
@FXML
private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\21621\\OneDrive\\Bureau\\reclamation.pdf"));
    doc.open();

    Paragraph para = new Paragraph("Votre reclamation");
    doc.add(para);
    doc.add(new Paragraph(" "));
    doc.add(new Paragraph(" "));
    doc.add(new Paragraph(" "));

    ReclamationSrevice rs = new ReclamationSrevice() {};
    List<reclamation> reclamations = null;
    try {
        reclamations = rs.recuperer();
    } catch (SQLException ex) {
        Logger.getLogger(AfficherRController.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Add the reclamations data to the PDF document
    for (reclamation reclamation : reclamations) {
        doc.add(new Paragraph("Nom du client: " + reclamation.getNom_client()));
        doc.add(new Paragraph("Sujet: " + reclamation.getSujet()));
        doc.add(new Paragraph("Etat: " + reclamation.getEtat()));
        doc.add(new Paragraph(" "));
    }

    doc.close();
}

    @FXML
    private void envoyer(ActionEvent event) {
        // Récupération des informations du formulaire
        String from = fromField.getText();
        String to = toField.getText();
        String message = messagetf.getText();

        // Configuration des propriétés du serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Création de l'objet Session pour se connecter au serveur SMTP
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
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
}
    
    

