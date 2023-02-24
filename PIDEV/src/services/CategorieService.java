package services;

import entities.Categorie;
import entities.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class CategorieService implements IService<Categorie> {

    Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String query = "INSERT INTO categorie(id_categorie,categorieVoiture) VALUES(?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1,categorie.getIdcategorie());
            statement.setString(2,categorie.getCategorieVoiture());
           statement.executeUpdate();
        }
       catch (IllegalArgumentException ex) {
               System.out.println(ex.getMessage());
               }          
       
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        String query = "UPDATE categorie SET  categorieVoiture = ?, where id_categorie = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1,categorie.getCategorieVoiture());
            statement.setInt(2,categorie.getIdcategorie());
            

            statement.executeUpdate();
        }
        
    }

    @Override
    public void supprimer(Categorie categorie) throws SQLException {
        
        String query = "DELETE FROM categorie WHERE id_categorie = ?" ;
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1,categorie.getIdcategorie());
                        statement.executeUpdate();

    }
    }
    
 
    public List<Categorie> recuperer() throws SQLException{
        String query = "SELECT * FROM categorie";
        List<Categorie> categories = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Categorie categorie = new Categorie();
            categorie.setIdcategorie(resultSet.getInt("id_categorie"));
            categorie.setCategorieVoiture(resultSet.getString("categorieVoiture"));
            categories.add(categorie);
                }
            }
        }
        return categories;
    }

   
    
}
