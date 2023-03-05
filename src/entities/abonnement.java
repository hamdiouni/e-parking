
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;



/**
 *
 * @author Skander
 */
public class abonnement {
    
    private int id ;
    private String nom;
private float tarif;
    public abonnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public abonnement(int id,  String nom, float tarif) {
        this.id = id;
        this.nom = nom;
        this.tarif = tarif;
    }
      public abonnement(  String nom, float tarif) {
        this.nom = nom;
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "abonnement{" + "id=" + id + ", nom=" + nom + ", tarif=" + tarif + '}';
    }
       
}
