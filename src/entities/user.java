package entities;

public class user {

    public static Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id_user;
    private String username, password, email;
    private boolean bloque;

    public user() {
    }

    public user(int id_user, String username, String password, String email) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.email = email;
    }
 public user( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBloque() {
        return bloque;
    }

    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", username=" + username + ", password=" + password + ", email=" + email + '}';
    }

}
