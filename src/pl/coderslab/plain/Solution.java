package pl.coderslab.plain;

import pl.coderslab.utlis.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private double rate;
    private String commentary;
    private int id_exercise;
    private int id_users;

    public Solution(int id, String created, String updated, String description, double rate, String commentary, int id_exercise, int id_users) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.rate = rate;
        this.commentary = commentary;
        this.id_exercise = id_exercise;
        this.id_users = id_users;
    }

    public Solution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_exercise() {
        return id_exercise;
    }

    public void setId_exercise(int id_exercise) {
        this.id_exercise = id_exercise;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", commentary='" + commentary + '\'' +
                ", id_exercise=" + id_exercise +
                ", id_users=" + id_users +
                '}';
    }
}
