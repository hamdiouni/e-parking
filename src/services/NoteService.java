/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.note;
import entities.reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
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
    public static Document createPDF(ListView<note> listView) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
        document.open();

        ObservableList<note> items = listView.getItems();
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        for (note item : items) {
            table.addCell(new Paragraph(item.toString()));
        }

        document.add(table);
        document.close();

        return document;
    }

    @Override
     public List<note> rechercher(note note) throws SQLException {
    List<note> notes = new ArrayList<>();
    String query = "SELECT * FROM note WHERE nom_client = ?";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, note.getNom_client());
        
        
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                note ab = new note();
                ab.setNom_client(resultSet.getString("nom_client"));
                 ab.setMessage(resultSet.getString("message"));
                               

                
                notes.add(ab);
            }
        }
    }
    return notes;
}
     
   public List<note> trierParTarif() throws SQLException {
    List<note> notes = new ArrayList<>();
    String query = "SELECT * FROM note ORDER BY nom_client";

    try (PreparedStatement statement = cnx.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            note note = new note();
                    note.setId_note(resultSet.getInt("id_note"));
                    note.setNom_client(resultSet.getString("nom_client"));
                   
                    notes.add(note);
        }
    }
    return notes;
}
    

}
