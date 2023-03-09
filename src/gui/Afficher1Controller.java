package gui;

import entities.abonnement;
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
import services.abonnementService;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Afficher1Controller implements Initializable {

    @FXML
    private GridPane grid1;
    private abonnementService ps = new abonnementService();
    @FXML
    private TextField nomtf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<abonnement> abonnements = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < abonnements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User1.fxml"));
                AnchorPane pane = loader.load();

                //passage de parametres
                User1Controller controller = loader.getController();
                controller.setUser1(abonnements.get(i));

                grid1.add(pane, column, row);
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

void rechercher(ActionEvent event) throws SQLException, IOException {
    abonnement abonnement = new abonnement();
    abonnement.setNom(nomtf.getText());
         
    List<abonnement> abonnements = ps.rechercher(abonnement);
    grid1.getChildren().clear(); // vider le GridPane
    
    int row = 0;
    int column = 0;
    for (int i = 0; i < abonnements.size(); i++) {
        //chargement dynamique d'une interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User1.fxml"));
        AnchorPane pane = loader.load();

        //passage de parametres
        User1Controller controller = loader.getController();
        controller.setUser1(abonnements.get(i));

        grid1.add(pane, column, row);
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
        List<abonnement> abonnements = ps.trierParTarif();
        grid1.getChildren().clear(); // vider le GridPane
        
        int row = 0;
        int column = 0;
        for (int i = 0; i < abonnements.size(); i++) {
            //chargement dynamique d'une interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User1.fxml"));
            AnchorPane pane = loader.load();

            //passage de parametres
            User1Controller controller = loader.getController();
            controller.setUser1(abonnements.get(i));

            grid1.add(pane, column, row);
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

