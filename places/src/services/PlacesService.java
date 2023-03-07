/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Places;
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
public class PlacesService implements IService<Places> {

    Connection cnx;

    public PlacesService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
   public void ajouter(Places places) throws SQLException {
    try {
        
        places.setType(settype(places));
        String query = "INSERT INTO places (id_parking, nombre_places, type, prop_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, places.getId_parking());
            statement.setInt(2, places.getNombres_place());
            statement.setString(3, places.getType());
            statement.setInt(4, places.getProp_id());
            statement.executeUpdate();
        }
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
}
   public Places getPlaceById(int id) throws SQLException {
        String query = "SELECT * FROM places WHERE id_places = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Places place = new Places();
                    place.setProp_id(resultSet.getInt("prop_id"));
                    place.setId_places(resultSet.getInt("id_place"));
                    place.setId_parking(resultSet.getInt("id_parking"));
                    place.setNombres_place(resultSet.getInt("nombre_places"));
                    place.setType(resultSet.getString("type"));
                    return place;
                }
            }
        }
        return null;
    }
   
    
    public Places getPlaceByUserId(int user_id) throws SQLException {
        String query = "SELECT * FROM places WHERE prop_id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, user_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Places place = new Places();
                    place.setProp_id(resultSet.getInt("prop_id"));
                    place.setId_places(resultSet.getInt("id_place"));
                    place.setId_parking(resultSet.getInt("id_parking"));
                    place.setNombres_place(resultSet.getInt("nombre_places"));
                    place.setType(resultSet.getString("type"));
                    return place;
                }
            }
        }
        return null;
    }
    
    /**
     *
     
     */
    @Override
    public List<Places> recuperer() throws SQLException{
        String query = "SELECT * FROM places";
        List<Places> places = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Places place = new Places();
                    place.setProp_id(resultSet.getInt("prop_id"));
                    place.setId_parking(resultSet.getInt("id_parking"));
                    place.setNombres_place(resultSet.getInt("nombre_places"));
                    place.setType(resultSet.getString("type"));
                    place.setId_places(resultSet.getInt("id_place"));
                    places.add(place);
                }
            }
        }
        return places;
    }
    
    /**
     *
     * @param user
     * @throws SQLException
     */
    
    public void modifier(Places place) throws SQLException {
        if (isUserValid(place)) 
        {String query = "UPDATE places SET id_parking = ?, nombre_places = ?,type = ?  WHERE id_place = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, place.getId_parking());
            statement.setInt(2, place.getNombres_place());
            statement.setString(3, place.getType());
            statement.setInt(4, place.getId_places());

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
    
    public void supprimer(Places place) throws SQLException {
        String query = "DELETE FROM places WHERE id_place = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, place.getId_places());
            statement.executeUpdate();
        }
    }
    
    public boolean isUserValid(Places place) {
        if (place.getId_parking()== 0 ) {
            return false;
        }
        if (place.getNombres_place()== 0 ) {
            return false;
        }
        return !(place.getType()== null || place.getType().isEmpty());
    }
    

    public String settype(Places places) {
        if (places.getType().matches("[a-zA-Z]+")) {
           return places.getType();
        } else {
            throw new IllegalArgumentException("Le nom d'utilisateur ne doit contenir que des lettres");
        }
    }
}
    
   
    