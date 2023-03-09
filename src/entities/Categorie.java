package entities;

/**
 *
 * @author Skander
 */
public class Categorie {
    private int id_categorie;
    private String categorieVoiture;

    public Categorie() {
    }

    public Categorie(int id_categorie ) {
                this.id_categorie = id_categorie;
        this.categorieVoiture = categorieVoiture;
        
    }

    public Categorie(String categorieVoiture) {
        this.categorieVoiture = categorieVoiture;
    }

   
    
    

    public int getIdcategorie() {
        return id_categorie;
    }

    public void setIdcategorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getCategorieVoiture() {
        return categorieVoiture;
    }

    public void setCategorieVoiture(String categorieVoiture) {
        this.categorieVoiture = categorieVoiture;
    }

    

    @Override

    
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", categorieVoiture=" + categorieVoiture +  '}';
    }

    
   

   

   
    
    
    
    
    
}
