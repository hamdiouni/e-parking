/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutervehiculeController implements Initializable {

    @FXML
    private ComboBox<?> categorietf;
    @FXML
    private TextField matriculetf;
    @FXML
    private TextField marquetf;
    @FXML
    private TextField modeletf;
    @FXML
    private TextField couleurtf;
   VehiculeService ps = new VehiculeService(){};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutervehicule(ActionEvent event) { 
        
        Vehicule p = new Vehicule();
        p.setMatricule(matriculetf.getText());
                p.setMarque(marquetf.getText());

                        p.setCouleur(couleurtf.getText());

                                p.setModele(modeletf.getText());

        
       // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void reset() {
        matriculetf.setText("");
        marquetf.setText("");
        modeletf.setText("");
        couleurtf.setText("");
categorietf.selectionModelProperty();
    }

    @FXML
    private void affichervehicule(ActionEvent event) {
        
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("affichervehicule.fxml"));
            matriculetf.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimervehicule(ActionEvent event) {
 Vehicule p = new Vehicule();

    p.setMatricule(matriculetf.getText());
        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifiervehicule(ActionEvent event) {
Vehicule p = new Vehicule();
        p.setMatricule(matriculetf.getText());
                p.setMarque(marquetf.getText());

                        p.setCouleur(couleurtf.getText());

                                p.setModele(modeletf.getText());

        
       // Valide l'objet User avant de le modifier
        try {
            ps.modifier(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
 
}
