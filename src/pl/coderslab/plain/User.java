package pl.coderslab.plain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String name;
    private String password; //zahashowane
    private String email; //unikatowy
    private int idUserGroup;

    public User() {
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User(String name, String email, String password, int idUserGroup) {
        this.name = name;
        this.hashPassword(password);
        this.email = email;
        this.idUserGroup = idUserGroup;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(int idUserGroup) {
        this.idUserGroup = idUserGroup;
    }
}
