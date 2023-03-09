/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.NoteService;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class AfficherNController implements Initializable {

    NoteService ps = new NoteService() {};

    @FXML
    private GridPane grid2;
    @FXML
    private TextField nommtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            List<note> notes = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < notes.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User5.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              User5Controller  controller = loader.getController();
                controller.setUser(notes.get(i));

                grid2.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void rechercher(ActionEvent event) throws SQLException, IOException {
           note note = new note();
    note.setNom_client(nommtf.getText());

            


    List<note> notes = ps.rechercher(note);
    grid2.getChildren().clear(); // vider le GridPane
    
    int row = 0;
    int column = 0;
    for (int i = 0; i < notes.size(); i++) {
        //chargement dynamique d'une interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User5.fxml"));
        AnchorPane pane = loader.load();

        //passage de parametres
        User5Controller controller = loader.getController();
        controller.setUser(notes.get(i));

        grid2.add(pane, column, row);
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
        List<note> notes = ps.trierParTarif();
        grid2.getChildren().clear(); // vider le GridPane
        
        int row = 0;
        int column = 0;
        for (int i = 0; i < notes.size(); i++) {
            //chargement dynamique d'une interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User5.fxml"));
            AnchorPane pane = loader.load();

            //passage de parametres
            User5Controller controller = loader.getController();
            controller.setUser(notes.get(i));

            grid2.add(pane, column, row);
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

    


    }

   

