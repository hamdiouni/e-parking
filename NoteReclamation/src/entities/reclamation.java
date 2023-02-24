/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author 21621
 */
public class reclamation {
    private int id_reclamtion; 
   private String nom_client,sujet,etat;

    public int getId_reclamtion() {
        return id_reclamtion;
    }

    public void setId_reclamtion(int id_reclamtion) {
        this.id_reclamtion = id_reclamtion;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public reclamation() {
    }

    public reclamation(int id_reclamtion, String nom_client, String sujet, String etat) {
        this.id_reclamtion = id_reclamtion;
        this.nom_client = nom_client;
        this.sujet = sujet;
        this.etat = etat;
    }
   
     public reclamation( String nom_client, String sujet, String etat) {
        this.nom_client = nom_client;
        this.sujet = sujet;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_reclamtion=" + id_reclamtion + ", nom_client=" + nom_client + ", sujet=" + sujet + ", etat=" + etat + '}';
    }
   
    
}
