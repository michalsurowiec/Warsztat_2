package pl.coderslab.dao;

import pl.coderslab.plain.User;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private final String URL =
            "jdbc:mysql://localhost:3306/java_warsztat_2?useSSL=false&characterEncoding=utf8";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(name, email, password, skills, user_group_id) VALUES (?, ?, ? ,? ,?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET name = ?, email = ?, password = ?, skills = ?, user_group_id = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_USERS_BY_USERGROUP_ID_QUERY =
            "SELECT * FROM users WHERE id_user_group = ?";

    public static User create(User user) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSkills());
            statement.setInt(5, user.getIdUserGroup());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setPassword(resultSet.getString(3));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static User read(int userId) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIdUserGroup(resultSet.getInt("user_group_id"));
                user.setSkills(resultSet.getString("skills"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(User user) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSkills());
            statement.setInt(5, user.getIdUserGroup());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User[] addToArray(User user, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = user;
        return tmpUsers;
    }

    public static User[] findAll() {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIdUserGroup(resultSet.getInt("user_group_id"));
                user.setSkills(resultSet.getString("skills"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public static User[] findAllByGroupId(int userGroupId) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_BY_USERGROUP_ID_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIdUserGroup(resultSet.getInt("user_group_id"));
                user.setSkills(resultSet.getString("skills"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

}
