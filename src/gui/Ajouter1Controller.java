/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.abonnement;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.abonnementService;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class Ajouter1Controller implements Initializable {
    abonnementService ps = new abonnementService() ;

  
    @FXML
    private TextField idtf;
    @FXML
    private TextField tariftf;
    @FXML
    private TextField nomtf;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public boolean isAbonnementValid(abonnement abonnement) {
    String nom = abonnement.getNom();
    double tarif = abonnement.getTarif();

    // Vérifier que le nom est l'un des noms prédéfinis (gold, silver, bronze) ou un nom personnalisé
    if (!nom.matches("^(gold|silver|bronze|[a-zA-Z]+)$")) {
        return false;
    }

    // Vérifier que le tarif est positif
    if (tarif < 0) {
        return false;
    }

    // Vérifier que le tarif correspond au nom du code d'abonnement
    switch (nom) {
        case "gold":
            if (tarif != 200.0) {
                return false;
            }
            break;
        case "silver":
            if (tarif != 150.0) {
                return false;
            }
            break;
        case "bronze":
            if (tarif != 80.0) {
                return false;
            }
            break;
    }

    // Si toutes les vérifications sont passées, renvoyer true
    return true;
}
    
    
    

     private void reset() {
        nomtf.setText("");
        tariftf.setText("");
    }

    @FXML
    private void ajouter(ActionEvent event) {
          abonnement p = new abonnement();
        p.setNom(nomtf.getText());
p.setTarif((float) Double.parseDouble(tariftf.getText()));
        if (isAbonnementValid(p)) { // Valide l'objet User avant de le modifier
        try {
            ps.ajouter(p);
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
          abonnement p = new abonnement();
       
 p.setId(Integer.parseInt(idtf.getText()));        try {
            ps.supprimer(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         abonnement p = new abonnement();
                 p.setId(Integer.parseInt(idtf.getText()));

        p.setNom(nomtf.getText());
p.setTarif((float) Double.parseDouble(tariftf.getText()));
        if (isAbonnementValid(p)) { // Valide l'objet User avant de le modifier
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
    private void afficher(ActionEvent event) { try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficher1.fxml"));
            nomtf.getScene().setRoot(loader);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleQuitter(ActionEvent event) {
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
        
        
        
        
        
        
        
        
        
        
        
    }
    

