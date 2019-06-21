package pl.coderslab.dao;

import pl.coderslab.plain.Solution;
import pl.coderslab.plain.User;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Na dnie jest zakomentowany findAll. Do sprawdzenia i poprawienia.
public class SolutionDao {

//    public Solution[] findAllByUserId (int userId) {//copy z userDAO = coś zmoenić
//        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
//            User[] users = new User[0];
//            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_BY_USER_ID_QUERY);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                user.setUserGroupId(resultSet.getInt("user_group_id"));
//                users = addToArray(user, users);
//            }
//            return users;
//        } catch (SQLException e) {
//            e.printStackTrace(); return null;
//        }
//    }

}
