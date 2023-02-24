/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class VehiculeService implements IService<Vehicule> {

    Connection cnx;

    public VehiculeService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vehicule vehicule) throws SQLException {
        try{
         vehicule.setMatricule(setmatricule(vehicule));
                  vehicule.setMarque(setmarque(vehicule));
         vehicule.setCouleur(setcouleur(vehicule));
         vehicule.setModele(setmodele(vehicule));

        if (isUserValid(vehicule)) 
        {String query = "INSERT INTO vehicule(matricule,modele,couleur,marque) VALUES(?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1,vehicule.getMatricule() );
            statement.setString(2,vehicule.getModele() );
            statement.setString(3,vehicule.getCouleur() );
           statement.setString(4,vehicule.getMarque() );
            statement.executeUpdate();
         } } else {
        System.out.println("L'objet User n'est pas valide.");
    }
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
                System.out.println("L'ajout n'est pas valide.");

    }
}

    @Override
    public void modifier(Vehicule vehicule) throws SQLException {
        String query = "UPDATE vehicule SET  modele = ?,couleur = ?, marque = ? where matricule = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1,vehicule.getMarque() );
            statement.setString(2,vehicule.getModele() );
            statement.setString(3,vehicule.getCouleur() );
            statement.setString(4,vehicule.getMatricule() );

            statement.executeUpdate();
        }
        
    }

    @Override
    public void supprimer(Vehicule vehicule) throws SQLException {
        
        String query = "DELETE FROM vehicule WHERE matricule = ?" ;
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1,vehicule.getMatricule() );
                        statement.executeUpdate();

    }
    }
    
 
    public List<Vehicule> recuperer() throws SQLException{
        String query = "SELECT * FROM vehicule";
        List<Vehicule> vehicules = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicule vehicule = new Vehicule();
                    vehicule.setMatricule(resultSet.getString("matricule"));
            vehicule.setMarque(resultSet.getString("marque"));
            vehicule.setModele(resultSet.getString("modele"));
            vehicule.setCouleur(resultSet.getString("couleur"));
            vehicules.add(vehicule);
                }
            }
        }
        return vehicules;
    } 
     public boolean isUserValid(Vehicule vehicule) {
        if (vehicule.getMatricule() == null || vehicule.getMatricule().isEmpty()) {
            return false;
        }
        if (vehicule.getMarque()== null || vehicule.getMarque().isEmpty()) {
            return false;
        } if (vehicule.getModele()== null || vehicule.getModele().isEmpty()) {
            return false;
        }
        return !(vehicule.getCouleur()== null || vehicule.getCouleur().isEmpty());
    }
    public String setmatricule(Vehicule vehicule) {
        if (vehicule.getMatricule().matches("[a-zA-Z1-9]+")) {
           return vehicule.getMatricule();
        } else {
            throw new IllegalArgumentException("Le matricule ne doit contenir que des lettres");
        }
    }

   public String setmodele(Vehicule vehicule) {
        if (vehicule.getModele().matches("[a-zA-Z]+")) {
           return vehicule.getModele();
        } else {
            throw new IllegalArgumentException("Le modele ne doit contenir que des lettres");
        }
    }

    public String setcouleur(Vehicule vehicule) {
       if (vehicule.getCouleur().matches("[a-zA-Z]+")) {
           return vehicule.getCouleur();
        } else {
            throw new IllegalArgumentException("Le couleur ne doit contenir que des lettres");
        }
    } 
    public String setmarque(Vehicule vehicule) {
       if (vehicule.getMarque().matches("[a-zA-Z]+")) {
           return vehicule.getMarque();
        } else {
            throw new IllegalArgumentException("Le marque ne doit contenir que des lettres");
        }
    }
    
}
