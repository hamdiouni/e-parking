/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Places;
import entities.Reservation;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import services.ReservationService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import services.PlacesService;
import utils.MyDB;


/**
 *
 * @author Skander
 */
public class test {
    
    
    public static void main(String[] args) {
       
        try {
            Reservation p;
            
            
          p = new Reservation(1,90 ,5.5f,"uneheure");
           
            ReservationService ps = new ReservationService() {};
          //ps.ajouter(p);
           //ps.modifier(p);
           ps.supprimer(p);
           System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
