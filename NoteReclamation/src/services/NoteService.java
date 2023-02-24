/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.note;
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
public class NoteService implements IService<note> {

    Connection cnx;

    public NoteService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(note note) throws SQLException {
        try {
note.setNom_client(setnom(note));
        note.setMessage(setmessage(note));
        String query = "INSERT INTO note (nom_client,message,date_note,rate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, note.getNom_client());
            statement.setString(2, note.getMessage());
            statement.setDate(3, (java.sql.Date) note.getDate());
            statement.setInt(4, note.getRate());
            statement.executeUpdate();
        }  
        } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }

    }

    @Override
    public void modifier(note t) throws SQLException {

        {
            String query = "UPDATE note SET nom_client = ?,message = ?,date_note = ? ,rate = ? where id_note = ?";
            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setString(1, t.getNom_client());
                statement.setString(2, t.getMessage());
                statement.setDate(3, (java.sql.Date) t.getDate());
                statement.setInt(4, t.getRate());
                statement.setInt(5, t.getId_note());

                statement.executeUpdate();
            }

        }
    }

    @Override
    public void supprimer(note t) throws SQLException {
        String query = "DELETE FROM note WHERE id_note = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, t.getId_note());
            statement.executeUpdate();
        }
    }

    public boolean isUserValid(note note) {
        if (note.getNom_client() == null || note.getNom_client().isEmpty()) {
            return false;
        }
        if (note.getNom_client() == null || note.getNom_client().isEmpty()) {
            return false;
        }
        return !(note.getMessage() == null || note.getMessage().isEmpty());
    }

    

    @Override
    public List<note> recuperer() throws SQLException {
        List<note> notes = new ArrayList<>();
        String s = "select * from note";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            note p = new note();
            p.setId_note(rs.getInt("id_note"));
            p.setNom_client(rs.getString("nom_client"));
            p.setMessage(rs.getString("message"));
            p.setDate(rs.getDate("date_note"));
            p.setRate(rs.getInt("rate"));

            notes.add(p);

        }
        return notes;
    }
    public String setnom(note note) {
        if (note.getNom_client().matches("[a-zA-Z]+")) {
            return note.getNom_client();
        } else {
            throw new IllegalArgumentException("Le nom de client ne doit contenir que des lettres");
        }
    }

    public String setmessage(note note) {
        if (note.getMessage().matches("[a-zA-Z]+")) {
            return note.getMessage();
        } else {
            throw new IllegalArgumentException("Le message ne doit contenir que des lettres");
        }
    }


}
