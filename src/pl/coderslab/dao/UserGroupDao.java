package pl.coderslab.dao;

import pl.coderslab.plain.UserGroup;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.*;
import java.util.Arrays;

public class UserGroupDao {

    private static final String CREATE_USERGROUP_QUERY =
            "INSERT INTO user_group VALUES (null, ?)";
    private static final String READ_USERGROUP_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USERGROUP_QUERY =
            "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_USERGROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USERGROUPS_QUERY =
            "SELECT * FROM user_group";

    public static UserGroup create(UserGroup userGroup) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userGroup.setId(resultSet.getInt(1));
            }
            return userGroup;
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            //e.printStackTrace();
            return null;
        }
    }

    public static UserGroup read(int userGroupId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USERGROUP_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt(1));
                userGroup.setName(resultSet.getString(2));
                return userGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(UserGroup userGroup) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_QUERY);
            statement.setString(1, userGroup.getName());
            statement.setInt(2, userGroup.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            //e.printStackTrace();
        }
    }

    public static void delete(int userGroupId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            UserDao.deleteByGroupId(userGroupId);
            PreparedStatement statement = conn.prepareStatement(DELETE_USERGROUP_QUERY);
            statement.setInt(1, userGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserGroup[] addToArray(UserGroup userGroup, UserGroup[] userGroups) {
        UserGroup[] tmpUserGroup = Arrays.copyOf(userGroups, userGroups.length + 1);
        tmpUserGroup[userGroups.length] = userGroup;
        return tmpUserGroup;
    }

    public static UserGroup[] findAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            UserGroup[] userGroups = new UserGroup[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERGROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt(1));
                userGroup.setName(resultSet.getString(2));
                userGroups = addToArray(userGroup, userGroups);
            }
            return userGroups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

}
