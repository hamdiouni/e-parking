/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static javax.print.attribute.standard.MediaSize.Engineering.E;
import services.VehiculeService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutervehiculeController implements Initializable {

    @FXML
    private ComboBox<String> categorietf;
    @FXML
    private TextField matriculetf;
    @FXML
    private TextField marquetf;
    @FXML
    private TextField modeletf;
    @FXML
    private TextField couleurtf;
   VehiculeService ps = new VehiculeService(){};
   Vehicule v= new Vehicule();
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cnx = MyDB.getInstance().getCnx();

        
      ObservableList<String> modeleList = FXCollections.observableArrayList();
try {
    // Remplacez les informations de connexion par celles de votre base de données
    

    // Créez une connexion à la base de données

    // Exécutez une requête SQL pour récupérer les modèles de votre base de données
    Statement stmt = cnx.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT categorieVoiture FROM categorie");

    // Parcourez les résultats de la requête et ajoutez chaque modèle à la liste observable
    while (rs.next()) {
        modeleList.add(rs.getString("categorieVoiture"));
    }

    // Fermez la connexion à la base de données

} catch (SQLException e) {
    e.printStackTrace();
}

// Définissez la liste observable comme la source de données de la liste déroulante
categorietf.setItems(modeleList);




// Ajout de l'ObservableList à la ComboBox
        
        // TODO
    }    

    @FXML
    private void ajoutervehicule(ActionEvent event) { 
           ObservableList<String> modeleList = FXCollections.observableArrayList();
try {
    // Remplacez les informations de connexion par celles de votre base de données
    

    // Créez une connexion à la base de données

    // Exécutez une requête SQL pour récupérer les modèles de votre base de données
    Statement stmt = cnx.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT categorieVoiture FROM categorie");

    // Parcourez les résultats de la requête et ajoutez chaque modèle à la liste observable
    while (rs.next()) {
        modeleList.add(rs.getString("categorieVoiture"));
    }

    // Fermez la connexion à la base de données

} catch (SQLException e) {
    e.printStackTrace();
}

// Définissez la liste observable comme la source de données de la liste déroulante

        
        Vehicule p = new Vehicule();
        p.setMatricule(matriculetf.getText());
                p.setMarque(marquetf.getText());

                        p.setCouleur(couleurtf.getText());

                                p.setModele(modeletf.getText());
p.setCategorieVoiture(categorietf.getValue());
        
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
