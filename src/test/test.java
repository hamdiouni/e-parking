/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.note;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import services.NoteService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import utils.MyDB;


/**
 *
 * @author Skander
 */
public class test {
    
    
    public static void main(String[] args) {
       
        try {
            note p;
            
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2022);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 23);
            java.util.Date spec=calendar.getTime();
            Date sqldate=new Date(spec.getTime());
          p = new note(7, "hamdi", "hhhhh", sqldate,5);
           
            NoteService ps = new NoteService();
            //ps.ajouter(p);
           ps.modifier(p);
          // ps.supprimer(p);
          System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
