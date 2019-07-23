package pl.coderslab.main;

import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.User;
import pl.coderslab.plain.UserGroup;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

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
        boolean isUserLogged = false;
        String email = "";
        while (isUserLogged){
            System.out.println("Podaj swój email lub wpisz null jeżeli chcesz zrezygnować z logowania");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.nextLine().equals("null")) {
                email = scanner.next();
                User user = UserDao.readByEmail(email);
                if (user.equals(null)){
                    System.out.println("Taki użytkownik nie istnieje! Spróbuj ponownie");
                } else {
                    User userLog = new User();
                    scanner.reset();
                    System.out.println("Wpisz hasło");
                    userLog.hashPassword(scanner.next());
                    if (userLog.getPassword().equals(user.getPassword())){
                        System.out.println("Brawo! Zalogowałeś się");
                        isUserLogged = true;
                    } else {
                        System.out.println("Niepoprawne hasło! Zaloguj się ponownie");
                    }
                }
            } else {
                break;
            }
        }

        if (isUserLogged) {
            String[] user = {email};
            UserHub.main(user);
        }
    }



}
