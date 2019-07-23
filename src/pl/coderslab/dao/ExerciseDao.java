package pl.coderslab.dao;

import pl.coderslab.plain.Exercise;
import pl.coderslab.utlis.DatabaseUtils;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {

    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise VALUES (null, ?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY =
            "SELECT * FROM exercise";

    public static Exercise create(Exercise exercise) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Exercise read(int exerciseId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt(1));
                exercise.setTitle(resultSet.getString(2));
                exercise.setDescription(resultSet.getString(3));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Exercise exercise) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int exerciseId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            SolutionDao.deleteByExerciseId(exerciseId);
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Exercise[] addToArray(Exercise exercise, Exercise[] exercises) {
        Exercise[] tmpExercise = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercise[exercises.length] = exercise;
        return tmpExercise;
    }

    public static Exercise[] findAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt(1));
                exercise.setTitle(resultSet.getString(2));
                exercise.setDescription(resultSet.getString(3));
                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

}
