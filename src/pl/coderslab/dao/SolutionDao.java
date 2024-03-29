package pl.coderslab.dao;

import pl.coderslab.plain.Solution;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution VALUES (null, CURRENT_TIMESTAMP, null, ?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution WHERE id = ?";
    private static final String READ_SOLUTION_BY_USER_ID_AND_EXERCISE_ID_QUERY =
            "SELECT * FROM solution WHERE id_users = ? AND id_exercise = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET updated = CURRENT_TIMESTAMP, description = ?, rate = ?, commentary = ?, id_exercise = ?, id_users = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String DELETE_SOLUTION_BY_USER_ID_QUERY =
            "DELETE FROM solution WHERE id_users = ?";
    private static final String DELETE_SOLUTION_BY_EXERCISE_ID_QUERY =
            "DELETE FROM solution WHERE id_exercise = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY =
            "SELECT * FROM solution WHERE id_users = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY =
            "SELECT * FROM solution WHERE id_exercise = ?";
    private static final String FIND_ALL_SOLUTIONS_WITH_0_RATE_AND_NOTNULL_DESCRIPTION_QUERY =
            "SELECT * FROM solution WHERE description IS NOT NULL AND rate = 0.0";

    public static Solution create(Solution solution) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getDescription());
            statement.setDouble(2, solution.getRate());
            statement.setString(3, solution.getCommentary());
            statement.setInt(4, solution.getId_exercise());
            statement.setInt(5, solution.getId_users());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            //e.printStackTrace();
            return null;
        }
    }

    public static Solution read(int solutionId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
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

    public static Solution readByUserIdAndExerciseId(int userId, int exerciseId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_BY_USER_ID_AND_EXERCISE_ID_QUERY);
            statement.setInt(1, userId);
            statement.setInt(2, exerciseId);
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
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getDescription());
            statement.setDouble(2, solution.getRate());
            statement.setString(3, solution.getCommentary());
            statement.setInt(4, solution.getId_exercise());
            statement.setInt(5, solution.getId_users());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            //e.printStackTrace();
        }
    }

    public static void delete(int solutionId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByUserId(int userId){
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByExerciseId(int exerciseId){
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exerciseId);
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
        try (Connection conn = DatabaseUtils.getConnection()) {
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
            e.printStackTrace();
            return null;
        }
    }

    public static Solution[] findAllByUserId(int userId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
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
            e.printStackTrace();
            return null;
        }
    }

    public static Solution[] findAllByExerciseId(int exerciseId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exerciseId);
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
            e.printStackTrace();
            return null;
        }
    }

    public static Solution[] findAllByNotNullDescriptionAndRateEqualZero() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_WITH_0_RATE_AND_NOTNULL_DESCRIPTION_QUERY);
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
            e.printStackTrace();
            return null;
        }
    }

}
