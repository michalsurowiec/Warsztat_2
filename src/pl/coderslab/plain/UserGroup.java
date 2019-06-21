package pl.coderslab.plain;

public class UserGroup {
    private int id;
    private String name;

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
