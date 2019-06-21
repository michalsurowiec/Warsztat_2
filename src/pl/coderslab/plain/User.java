package pl.coderslab.plain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String name;
    private String password; //zahashowane
    private String email; //unikatowy
    private String skills;
    private int idUserGroup;

    public User() {
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User(int id, String name, String password, String email, String skills, int idUserGroup) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.skills = skills;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
