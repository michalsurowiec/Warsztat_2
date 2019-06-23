package pl.coderslab.main;

import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.User;

import java.sql.*;
import java.util.Arrays;

public class Main {

    /*
    Do przetestowania:
    UserDao
    SolutionDao
    GroupDao
    ExerciseDao
    ExerciseManagement
    UserManagement
    UserGroupManagement
    AssignExerciseToUser

    Do zrobienia: AddingSolution i UserDao
     */
    public static void main(String[] args) {

        User user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setEmail("testemail");
        UserDao.create(user);

    }



}
