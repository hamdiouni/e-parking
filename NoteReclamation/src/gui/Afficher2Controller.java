/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.note;
import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.NoteService;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Afficher2Controller implements Initializable {

    NoteService ps = new NoteService() {};

    @FXML
    private GridPane grid2;

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
                controller.setUser5(notes.get(i));

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
    
}
