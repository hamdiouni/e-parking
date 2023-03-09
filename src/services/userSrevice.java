/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.MyDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Skander
 */
public abstract class userSrevice implements IService<user> {

    Connection cnx;

    public userSrevice() {
        cnx = MyDB.getInstance().getCnx();
    }

   


public class PasswordEncryptor {

    private Connection cnx;

}

    

    public static String encryptPassword(String password) {
        String encryptedPassword = "";

        try {
            // Création d'une instance de l'algorithme MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Conversion du mot de passe en bytes et calcul de la somme de contrôle
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);

            // Conversion du hash en une chaîne hexadécimale
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            encryptedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encryptedPassword;
    }

    public void ajouter(user user) throws SQLException {
        try {
            user.setUsername(setusername(user));
            user.setPassword(encryptPassword(user.getPassword())); // Crypter le mot de passe
            user.setEmail(setemail(user));

            // Vérifier si l'utilisateur existe déjà
            if (utilisateurExiste(user)) {
                throw new IllegalArgumentException("Cet utilisateur existe déjà.");
            }

            String query = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
            }

            // Afficher l'alerte en cas de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur ajouté avec succès.");
            alert.showAndWait();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

   



private boolean utilisateurExiste(user user) throws SQLException {
    String query = "SELECT COUNT(*) FROM user WHERE username = ? OR email = ?";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        try (ResultSet result = statement.executeQuery()) {
            result.next();
            return result.getInt(1) > 0;
        }
    }
}





    
    public user getUserById(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE id_user = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user user = new user();
                    user.setId_user(resultSet.getInt("id_user"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    return user;
                }
            }
        }
        return null;
    }
    
    /**
     *
     
     */
    @Override
    public List<user>recuperer() throws SQLException{
        String query = "SELECT * FROM user";
        List<user> users = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user user = new user();
                    user.setId_user(resultSet.getInt("id_user"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    users.add(user);
                }
            }
        }
        return users;
    }
    
    /**
     *
     * @param user
     * @throws SQLException
     */
    
    public void modifier(user user) throws SQLException {
        if (isUserValid(user)) 
        {String query = "UPDATE user SET username = ?, password = ?,email = ?  WHERE id_user = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId_user());

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
    
    public void supprimer(user user) throws SQLException {
        String query = "DELETE FROM user WHERE email = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        }
    }
       
    
    public boolean isUserValid(user user) {
        if (user.getUsername()== null || user.getUsername().isEmpty()) {
            return false;
        }
        if (user.getPassword()== null || user.getPassword().isEmpty()) {
            return false;
        }
        return !(user.getEmail()== null || user.getEmail().isEmpty());
    }
    public String setusername(user user) {
        if (user.getUsername().matches("[a-zA-Z]+")) {
           return user.getUsername();
        } else {
            throw new IllegalArgumentException("Le nom d'utilisateur ne doit contenir que des lettres");
        }
    }

   

    public String setemail(user user) {
        Pattern pattern = Pattern.compile(".+@.+\\..+");
        Matcher matcher = pattern.matcher(user.getEmail());
        if (matcher.matches()) {
            return user.getEmail();
        } else {
            throw new IllegalArgumentException("L'adresse e-mail doit contenir '@'");
        }
    }
public void bloquerUtilisateur(user user) throws SQLException {
        String query = "UPDATE user SET bloque = true WHERE id_user = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, user.getId_user());
            statement.executeUpdate();
        }
    }

    public void debloquerUtilisateur(user user) throws SQLException {
        String query = "UPDATE user SET bloque = true WHERE id_user = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, user.getId_user());
            statement.executeUpdate();
        }
    }


}
    
   
    