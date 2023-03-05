package gui;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import utils.MyDB;

public class LoginManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/e-parking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection cnx;

    public LoginManager() {
        cnx = MyDB.getInstance().getCnx();
    }

    public boolean authenticateUser(String email, String password) {
        String query = "SELECT * FROM user WHERE email=? AND password=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmailExists(String email) {
        String query = "SELECT * FROM user WHERE email=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = rand.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE user SET password=? WHERE email=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setString(2, email);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
