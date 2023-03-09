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
public class Vehicule {

    
    
    private String marque, modele,couleur,matricule,categorieVoiture;

    public String getCategorieVoiture() {
        return categorieVoiture;
    }

    public void setCategorieVoiture(String categorieVoiture) {
        this.categorieVoiture = categorieVoiture;
    }

    public Vehicule() {
    }

    public Vehicule(String marque, String modele, String couleur, String matricule, String categorieVoiture) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.matricule = matricule;
        this.categorieVoiture = categorieVoiture;
    }

    
    
    

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }


    @Override
    public String toString() {
        return "Vehicule{" + "marque=" + marque + ", modele=" + modele + ", couleur=" + couleur + ", matricule=" + matricule + ", categorieVoiture=" + categorieVoiture + '}';
    }

    
   
    
   

   

   
    
    
    
    
    
}
