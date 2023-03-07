
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
public class Reservation {
    
    private int id_reservation,id_place;
    private float prix;
    private String duree;

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", id_place=" + id_place + ", prix=" + prix + ", duree=" + duree + '}';
    }

    public Reservation() {
    }

    public Reservation(int id_reservation, int id_place, float prix, String duree) {
        this.id_reservation = id_reservation;
        this.id_place = id_place;
        this.prix = prix;
        this.duree = duree;
    }
    public Reservation( int id_place, float prix, String duree) {
        this.id_place = id_place;
        this.prix = prix;
        this.duree = duree;
    }

   

   
    
    

   
    
    
    
    
    
}
