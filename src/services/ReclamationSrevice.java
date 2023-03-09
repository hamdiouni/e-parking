/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.note;
import entities.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public abstract class ReclamationSrevice implements IService<reclamation> {

    Connection cnx;

    public ReclamationSrevice() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
   public void ajouter(reclamation reclamation) throws SQLException {
    try {
        reclamation.setNom_client(setnom(reclamation));
        reclamation.setSujet(setsujet(reclamation));
        reclamation.setEtat(setetat(reclamation));
        String query = "INSERT INTO reclamation (nom_client, sujet, etat) VALUES (?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, reclamation.getNom_client());
            statement.setString(2, reclamation.getSujet());
            statement.setString(3, reclamation.getEtat());
            statement.executeUpdate();
        }
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    public reclamation getUserById(int id) throws SQLException {
        String query = "SELECT * FROM reclamation WHERE id_reclamation = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reclamation reclamation = new reclamation();
                    reclamation.setId_reclamtion(resultSet.getInt("id_reclamation"));
                    reclamation.setNom_client(resultSet.getString("nom_client"));
                    reclamation.setSujet(resultSet.getString("sujet"));
                    reclamation.setEtat(resultSet.getString("etat"));
                    return reclamation;
                }
            }
        }
        return null;
    }
    
    /**
     *
     
     */
    @Override
    public List<reclamation>recuperer() throws SQLException{
        String query = "SELECT * FROM reclamation";
        List<reclamation> reclamations = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reclamation reclamation = new reclamation();
                    reclamation.setId_reclamtion(resultSet.getInt("id_reclamation"));
                    reclamation.setNom_client(resultSet.getString("nom_client"));
                    reclamation.setSujet(resultSet.getString("sujet"));
                    reclamation.setEtat(resultSet.getString("etat"));
                    reclamations.add(reclamation);
                }
            }
        }
        return reclamations;
    }
    
    /**
     *
     * @param user
     * @throws SQLException
     */
    
    public void modifier(reclamation reclamation) throws SQLException {
        if (isUserValid(reclamation)) 
        {String query = "UPDATE reclamation SET nom_client = ?, sujet = ?,etat = ?  WHERE id_reclamation = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, reclamation.getNom_client());
            statement.setString(2, reclamation.getSujet());
            statement.setString(3, reclamation.getEtat());
            statement.setInt(4, reclamation.getId_reclamtion());

            statement.executeUpdate();
        }
        } else {
        System.out.println("L'objet User n'est pas valide.");
    }
    }
    
    /**
     *
     * @param user
     * @throws SQLException
     */
    
    public void supprimer(reclamation reclamation) throws SQLException {
        String query = "DELETE FROM reclamation WHERE id_reclamation = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, reclamation.getId_reclamtion());
            statement.executeUpdate();
        }
    }
    
    public boolean isUserValid(reclamation reclamation) {
        if (reclamation.getNom_client() == null || reclamation.getNom_client().isEmpty()) {
            return false;
        }
        if (reclamation.getSujet()== null || reclamation.getSujet().isEmpty()) {
            return false;
        }
        return !(reclamation.getEtat()== null || reclamation.getEtat().isEmpty());
    }
    public String setnom(reclamation reclamation) {
        if (reclamation.getNom_client().matches("[a-zA-Z]+")) {
           return reclamation.getNom_client();
        } else {
            throw new IllegalArgumentException("Le nom de client ne doit contenir que des lettres");
        }
    }

   public String setsujet(reclamation reclamation) {
        if (reclamation.getSujet().matches("[a-zA-Z]+")) {
           return reclamation.getSujet();
        } else {
            throw new IllegalArgumentException("Le sujet ne doit contenir que des lettres");
        }
    }

    public String setetat(reclamation reclamation) {
        if (reclamation.getEtat().matches("[a-zA-Z]+")) {
           return reclamation.getEtat();
        } else {
            throw new IllegalArgumentException("L'etat ne doit contenir que des lettres");
        }
    }
   public List<reclamation> rechercher(reclamation reclamation) throws SQLException {
    List<reclamation> reclamations = new ArrayList<>();
    String query = "SELECT * FROM reclamation WHERE nom_client = ?";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, reclamation.getNom_client());
        
        
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                reclamation ab = new reclamation();
                ab.setNom_client(resultSet.getString("nom_client"));
                 ab.setEtat(resultSet.getString("etat"));
                                  ab.setSujet(resultSet.getString("sujet"));

                
                reclamations.add(ab);
            }
        }
    }
    return reclamations;
}
    public List<reclamation> trierParTarif() throws SQLException {
    List<reclamation> reclamations = new ArrayList<>();
    String query = "SELECT * FROM reclamation ORDER BY nom_client";

    try (PreparedStatement statement = cnx.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            reclamation reclamation = new reclamation();
                    reclamation.setId_reclamtion(resultSet.getInt("id_reclamation"));
                    reclamation.setNom_client(resultSet.getString("nom_client"));
                    reclamation.setSujet(resultSet.getString("sujet"));
                    reclamation.setEtat(resultSet.getString("etat"));
                    reclamations.add(reclamation);
        }
    }
    return reclamations;
}
}
    
   
    