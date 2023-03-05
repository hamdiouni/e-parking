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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.ReclamationSrevice;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class AfficherController implements Initializable {

    @FXML
    private GridPane grid;
    ReclamationSrevice ps = new ReclamationSrevice() {};
    @FXML
    private TextField nommtf;
  Connection cnx;

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              UserController  controller = loader.getController();
                controller.setUser(reclamations.get(i));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        AnchorPane pane = loader.load();

        //passage de parametres
        UserController controller = loader.getController();
        controller.setUser(reclamations.get(i));

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            AnchorPane pane = loader.load();

            //passage de parametres
            UserController controller = loader.getController();
            controller.setUser(reclamations.get(i));

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

    try {
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Youssef\\Desktop\\djo\\reclamation.pdf"));
        doc.open();
    

        // Get all events from the database
        String query = "SELECT * FROM reclamation";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Add event information to the PDF
        doc.add(new Paragraph("Liste des reclamation :"));
        doc.add(new Paragraph("------------------------\n"));

        while (rs.next()) {
            String nom = rs.getString("nom_client");
              String Sujet = rs.getString("sujet");
            String Etat = rs.getString("etat");
          
 

            doc.add(new Paragraph("nom_client : " + nom));
             doc.add(new Paragraph("sujet : " + Sujet));
            doc.add(new Paragraph("etat  : " + Etat));
         
      
        
            doc.add(new Paragraph("\n"));
        }

        doc.close();
        Desktop.getDesktop().open(new File("C:\\Users\\Youssef\\Desktop\\djo\\reclamation.pdf"));

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}
