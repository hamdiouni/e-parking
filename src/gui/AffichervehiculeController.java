/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Vehicule;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.VehiculeService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AffichervehiculeController implements Initializable {

    @FXML
    private GridPane grid;
   VehiculeService ps = new VehiculeService(){};
    @FXML
    private TextField matricule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {
            List<Vehicule> Vehicules = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < Vehicules.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicule.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              VehiculeController  controller = loader.getController();
                controller.setVehicule(Vehicules.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }        // TODO
    }    

 

    @FXML
    private void recherche(ActionEvent event) throws SQLException, IOException {
        
        Vehicule vehicule = new Vehicule();
    vehicule.setMatricule(matricule.getText());

            


    List<Vehicule> vehicules = ps.rechercher(vehicule);
    grid.getChildren().clear(); // vider le GridPane
    
    int row = 0;
    int column = 0;
    for (int i = 0; i < vehicules.size(); i++) {
        //chargement dynamique d'une interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicule.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              VehiculeController  controller = loader.getController();
                controller.setVehicule(vehicules.get(i));

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
        List<Vehicule> vehicules = ps.trierParTarif();
        grid.getChildren().clear(); // vider le GridPane
        
        int row = 0;
        int column = 0;
        for (int i = 0; i < vehicules.size(); i++) {
            //chargement dynamique d'une interface
          FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicule.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
              VehiculeController  controller = loader.getController();
                controller.setVehicule(vehicules.get(i));

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
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistiques.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }




}