
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.sql.Date;


/**
 *
 * @author Skander
 */
public class note {

    public static int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int id_note, rate ;
    private String nom_client, message;
     private Date date_note;

    public note() {
    }

    public note(int id_note, String nom_client, String message, Date date_note,int rate ) {
        this.id_note = id_note;
        this.nom_client = nom_client;
        this.message = message;
        this.date_note = date_note;
         this.rate = rate;
    }
    public note(String nom_client, String message, Date date_note,int rate) {
        this.nom_client = nom_client;
        this.message = message;
        this.date_note = date_note;
         this.rate = rate;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String nom) {
        this.message = message;
    }

    public Date getDate() {
        return date_note;
    }

    public void setDate(Date date_note) {
        this.date_note = date_note;
    }
      public int getRate() {
        return rate;
    }
        public void setRate(int rate) {
        this.id_note = rate;
    }


    @Override
    public String toString() {
        return "note{" + "id_note=" + id_note + ", nom_client=" + nom_client + ", message=" + message + ", date_note=" + date_note+ ", rate=" + rate + '}';
    }

   
    
    

   
    
    
    
    
    
}
