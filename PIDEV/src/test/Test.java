/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Vehicule;
import java.sql.SQLException;
import services.VehiculeService;
import utils.MyDB;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        try {
            Vehicule v = new Vehicule("120 tunis","e36","rouge","bmw" );
            VehiculeService ps = new VehiculeService();
            ps.ajouter(v);
            //ps.modifier(v);
            System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
