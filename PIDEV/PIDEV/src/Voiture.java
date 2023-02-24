public class Voiture {
    private String marque;
    private String modele;
    private String couleur;
    private String categorie;
    private int idVoiture;

    public Voiture(String marque, String modele, String couleur, String categorie, int idVoiture) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.categorie = categorie;
        this.idVoiture = idVoiture;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    // Autres méthodes pour gérer les voitures, comme l'affichage des informations, la comparaison de voitures, etc.
}


