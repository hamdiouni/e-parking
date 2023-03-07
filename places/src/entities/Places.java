/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Places {

    public int getProp_id() {
        return prop_id;
    }

    public void setProp_id(int prop_id) {
        this.prop_id = prop_id;
    }
    
   private int id_places,id_parking ,nombres_place,prop_id;
           private String type;

    public int getId_parking() {
        return id_parking;
    }

    public void setId_parking(int id_parking) {
        this.id_parking = id_parking;
    }

    public int getId_places() {
        return id_places;
    }

    public void setId_places(int id_places) {
        this.id_places = id_places;
    }

    public int getNombres_place() {
        return nombres_place;
    }

    public void setNombres_place(int nombres_place) {
        this.nombres_place = nombres_place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   

    public Places() {
    }

    public Places(int id_places, int id_parking, int nombres_place, String type) {
        this.id_parking = id_parking;
        this.id_places = id_places;
        this.nombres_place = nombres_place;
        this.type = type;
    }
  
     public Places( int id_parking, int nombres_place, String type) {
        this.id_parking = id_parking;
        this.nombres_place = nombres_place;
        this.type = type;
    }  

    @Override
    public String toString() {
        return "Places{" + "id_places=" + id_places + ", id_parking=" + id_parking + ", nombres_place=" + nombres_place + ", type=" + type + '}';
    }
     
}