/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Places;
import java.io.IOException;
import services.PlacesService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author 21621
 */
public class AjouterplaceController implements Initializable {

    
     
    PlacesService ps = new PlacesService();
    
    @FXML
    private Button ajouterBtn;
    
    @FXML
    private GridPane affichagetf;

    @FXML
    private TextField idtf;
    @FXML
    private TextField nbplacestf;
    @FXML
    private TextField typetf;
    @FXML
    private TextField id_parkingtf;

    /**
     * Initializes the controller class.
     */
    public void load(){
        try {
            //BADEL PS.recuperer b ps.getByUserId(user.id)
            List<Places> places = ps.recuperer();
            affichagetf.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < places.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Place.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                PlaceController  controller = loader.getController();
                controller.set(places.get(i));

                affichagetf.add(pane, column, row);
                row++;
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }      

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
}
    
 public boolean isUserValid(Places place) {
        if (place.getId_parking()== 0 ) {
            return false;
        }
        if (place.getNombres_place()== 0 ) {
            return false;
        }
        return !(place.getType()== null || place.getType().isEmpty());
    }
     

    @FXML
    private void ajouter(ActionEvent event) {
        Places p = new Places();
p.setId_parking(Integer.parseInt(id_parkingtf.getText())); 
p.setNombres_place(Integer.parseInt(nbplacestf.getText())); 

        p.setType(typetf.getText());
       if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
            reset();
            load();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("L'objet place n'est pas valide.");
    }
    }
 private void reset() {
        id_parkingtf.setText("");
        nbplacestf.setText("");
        typetf.setText("");
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("afficherplace.fxml"));
            id_parkingtf.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
                Places p = new Places();
                p.setId_places(Integer.parseInt(idtf.getText())); 

p.setId_parking(Integer.parseInt(id_parkingtf.getText())); 
p.setNombres_place(Integer.parseInt(nbplacestf.getText())); 

        p.setType(typetf.getText());
       if (isUserValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.modifier(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        System.out.println("L'objet User n'est pas valide.");
    }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Places p = new Places();
       
        p.setId_places(Integer.parseInt(idtf.getText()));
        try {
            ps.supprimer(p);
            reset();
            load();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    
}
