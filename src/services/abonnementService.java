/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.abonnement;
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
 * @author Youssef
 */
public class abonnementService implements IService<abonnement> {

    Connection cnx;

    public abonnementService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
   public void ajouter(abonnement abonnement) throws SQLException {
    if (!isAbonnementValid(abonnement)) {
        throw new IllegalArgumentException("Nom invalide ou tarif négatif.");
    }

    if (abonnement.getTarif() > 300.0) {
        throw new IllegalArgumentException("Tarif ne doit pas dépasser 300.0.");
    }

    String query = "SELECT COUNT(*) FROM abonnement WHERE nom = ?";
    try (PreparedStatement checkStatement = cnx.prepareStatement(query)) {
        checkStatement.setString(1, abonnement.getNom());
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            throw new IllegalArgumentException("Un abonnement avec ce nom existe déjà.");
        }
    }

    abonnement.setNom(setNom(abonnement));

    query = "INSERT INTO abonnement(tarif, nom) VALUES (?, ?)";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setDouble(1, abonnement.getTarif());
        statement.setString(2, abonnement.getNom());

        statement.executeUpdate();
    }
}


    @Override
    public void modifier(abonnement abonnement) throws SQLException {
                String query = "UPDATE abonnement SET  nom = ?,tarif = ?  WHERE id = ?";

         try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, abonnement.getNom());
                    statement.setDouble(2, abonnement.getTarif());

            statement.setInt(3, abonnement.getId());

            statement.executeUpdate();
        }
        
    }

    @Override
    public void supprimer(abonnement abonnement) throws SQLException {
String query = "DELETE FROM abonnement WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, abonnement.getId());
            statement.executeUpdate();
        }    }
    
        
public List<abonnement> rechercher(abonnement abonnement) throws SQLException {
    List<abonnement> abonnements = new ArrayList<>();
    String query = "SELECT * FROM abonnement WHERE nom = ?";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, abonnement.getNom());
        
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                abonnement ab = new abonnement();
                ab.setNom(resultSet.getString("nom"));
                abonnements.add(ab);
            }
        }
    }
    return abonnements;
}
@Override
public List<abonnement> trierParTarif() throws SQLException {
    List<abonnement> abonnements = new ArrayList<>();
    String query = "SELECT * FROM abonnement ORDER BY tarif";

    try (PreparedStatement statement = cnx.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            abonnement ab = new abonnement();
            ab.setNom(resultSet.getString("nom"));
            ab.setTarif(resultSet.getFloat("tarif"));
            abonnements.add(ab);
        }
    }
    return abonnements;
}




    @Override
    public List<abonnement> recuperer( ) throws SQLException {
         String query = "SELECT * FROM abonnement";
        List<abonnement> abonnements = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    abonnement abonnement = new abonnement();
                    abonnement.setId(resultSet.getInt("id"));
                    abonnement.setNom(resultSet.getString("nom"));
                    abonnement.setTarif((float) resultSet.getDouble("tarif"));                    abonnements.add(abonnement);
                }
            }
        }
        return abonnements;
            
        }
        
    
    public boolean isAbonnementValid(abonnement abonnement) {
    String nom = abonnement.getNom();
    double tarif = abonnement.getTarif();

    // Vérifier que le nom est l'un des noms prédéfinis (gold, silver, bronze) ou un nom personnalisé
    if (!nom.matches("^(gold|silver|bronze|[a-zA-Z]+)$")) {
        return false;
    }

    // Vérifier que le tarif est positif
    if (tarif < 0) {
        return false;
    }

    // Vérifier que le tarif correspond au nom du code d'abonnement
    switch (nom) {
        case "gold":
            if (tarif != 200.0) {
                return false;
            }
            break;
        case "silver":
            if (tarif != 150.0) {
                return false;
            }
            break;
        case "bronze":
            if (tarif != 80.0) {
                return false;
            }
            break;
    }

    // Si toutes les vérifications sont passées, renvoyer true
    return true;
}
     private String setNom(abonnement abonnement) {
        String nom = abonnement.getNom();
        if (nom.matches("^(gold|silver|bronze)$")) {
            return nom;
        }
        return "personalise";
    }

   


}
 
    

