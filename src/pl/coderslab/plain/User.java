package pl.coderslab.plain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String password; //zahashowane
    private String email; //unikatowy
    private String skills;
    private int idUserGroup;

    public User() {
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User(int id, String username, String password, String email, String skills, int idUserGroup) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.skills = skills;
        this.idUserGroup = idUserGroup;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", skills='" + skills + '\'' +
                ", idUserGroup=" + idUserGroup +
                '}';
    }
}
