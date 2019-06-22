package pl.coderslab.dao;

import pl.coderslab.plain.Solution;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution VALUES (null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null, null, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET updated = CURRENT_TIMESTAMP, description = ?, id_Exercise = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";
    private static final String UPDATE_RATE_IN_SOLUTION_QUERY =
            "UPDATE solution SET rate = ?, commentary = ? where id = ?";

    public static Solution create(Solution solution) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getId_exercise());
            statement.setInt(3, solution.getId_users());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
                solution.setCreated(resultSet.getString(2));
                solution.setUpdated(resultSet.getString(3));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Solution read(int solutionId) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt(1));
                solution.setCreated(resultSet.getString(2));
                solution.setUpdated(resultSet.getString(3));
                solution.setDescription(resultSet.getString(4));
                solution.setRate(resultSet.getDouble(5));
                solution.setCommentary(resultSet.getString(6));
                solution.setId_exercise(resultSet.getInt(7));
                solution.setId_users(resultSet.getInt(8));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Solution solution) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getId_exercise());
            statement.setInt(3, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int solutionId) {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Solution[] addToArray(Solution solution, Solution[] solutions) {
        Solution[] tmpsolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpsolutions[solutions.length] = solution;
        return tmpsolutions;
    }

    public static Solution[] findAll() {
        try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt(1));
                solution.setCreated(resultSet.getString(2));
                solution.setUpdated(resultSet.getString(3));
                solution.setDescription(resultSet.getString(4));
                solution.setRate(resultSet.getDouble(5));
                solution.setCommentary(resultSet.getString(6));
                solution.setId_exercise(resultSet.getInt(7));
                solution.setId_users(resultSet.getInt(8));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public static void updateRating (Solution solution) {
        if (solution.getRate() >= 2.0 && solution.getRate() <= 6.0 && (solution.getRate()) * 10 % 5 == 0) {
            try (Connection conn = DatabaseUtils.getConnection("java_warsztat_2")) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_RATE_IN_SOLUTION_QUERY);
                statement.setDouble(1, solution.getRate());
                statement.setString(2, solution.getCommentary());
                statement.setInt(3, solution.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Błędna wartość oceny!");
        }
    }

}