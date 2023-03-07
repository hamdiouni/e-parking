/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
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
public class ReservationService implements IService<Reservation> {

    Connection cnx;

    public ReservationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        try {
        
        reservation.setDuree(setduree(reservation));
        String query = "INSERT INTO reservation (id_place, prix, durée) VALUES (?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, reservation.getId_place());
            statement.setFloat(2, reservation.getPrix());
            statement.setString(3, reservation.getDuree());
            statement.executeUpdate();
        }
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @Override
    public void modifier(Reservation reservation) throws SQLException {
        
        if (isUserValid(reservation)) 
        {String query = "UPDATE reservation SET id_place = ?, prix = ?,durée = ?  WHERE id_reservation = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, reservation.getId_place());
            statement.setFloat(2, reservation.getPrix());
            statement.setString(3, reservation.getDuree());
            statement.setInt(4, reservation.getId_reservation());

            statement.executeUpdate();
        }
        } else {
        System.out.println("L'objet User n'est pas valide.");
    }
        
    }

    @Override
    public void supprimer(Reservation reservation) throws SQLException {
String query = "DELETE FROM reservation WHERE id_reservation = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, reservation.getId_reservation());
            statement.executeUpdate();
        }    }

    @Override
    public List<Reservation> recuperer( ) throws SQLException {
       String query = "SELECT * FROM reservation";
        List<Reservation> Reservations = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId_place(resultSet.getInt("id_place"));
                   reservation.setPrix(resultSet.getFloat("prix"));
                    reservation.setDuree(resultSet.getString("durée"));
                    reservation.setId_reservation(resultSet.getInt("id_reservation"));
                    Reservations.add(reservation);
                }
            }
        }
        return Reservations;
    }
     public String setduree(Reservation reservation) {
        if (reservation.getDuree().matches("[0-9a-zA-Z' ']+")) {
           return reservation.getDuree();
        } else {
            throw new IllegalArgumentException("Le nom d'utilisateur ne doit contenir que des lettres");
        }
    }
public boolean isUserValid(Reservation reservation) {
        if (reservation.getId_place()== 0 ) {
            return false;
        }
        if (reservation.getPrix()== 0 ) {
            return false;
        }
        return !(reservation.getDuree()== null ||reservation.getDuree().isEmpty());
    }
}
 
    

    