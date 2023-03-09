/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import services.CategorieService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField categorietf;
    Categorie c = new Categorie();
CategorieService ps = new CategorieService();
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        cnx = MyDB.getInstance().getCnx();

        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
          c.setCategorieVoiture(categorietf.getText());

        
       // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(c);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void reset() {
        
       
categorietf.setText("");
    }
    @FXML
    private void afficher(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichercat.fxml"));
            categorietf.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
