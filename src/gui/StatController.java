/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class StatController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<?, ?> barChart;
    private Connection cnx;
    private PreparedStatement prepare;
    private ResultSet result;
   
        
    public void chart(){
               cnx = MyDB.getInstance().getCnx();

        String chartSql= "Select date_note, SUM(rate) FROM note GROUP BY date_note ORDER BY TIMESTAMP(date_note) ASC LIMIT 8";
        
        try{
            
          XYChart.Series chartData = new XYChart.Series();
          prepare = cnx.prepareStatement(chartSql);
          result= prepare.executeQuery();
          
          while(result.next()){
              chartData.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
          }
          barChart.getData().add(chartData);
        }catch(Exception e){e.printStackTrace();}
             
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chart();
    }    

  
    
}
