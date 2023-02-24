
package entities;


import java.util.ArrayList;
import java.util.List;

public class CategorieVoiture {
    private int id_categorie;
    private List<String> types;

    public CategorieVoiture(int id_categorie) {
        this.id_categorie = id_categorie;
        types = new ArrayList<>();
        types.add("SUV");
        types.add("Compacte");
        types.add("Berline familiale");
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
