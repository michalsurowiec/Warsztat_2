package pl.coderslab.main;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.User;
import pl.coderslab.plain.UserGroup;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /*
    Do zrobienia:
    - AddingSolution
    - AssignExerciseToUser:
        - przeglądanie rozwiązań które nie zostały dodane
        - przeglądanie rozwiązań dla konkretnego zadania
    - UserManagement - dodanie informacji o zdolnościach kursanta
     */

    public static void main(String[] args) {
        boolean userIsLoggingIn = true;
        String email = "";
        while (userIsLoggingIn){
            System.out.println("Podaj swój email lub wpisz quit jeżeli chcesz zrezygnować z logowania");
            Scanner scanner = new Scanner(System.in);
            email = scanner.next();
            if (!(email.equals("quit"))) {
                User user = UserDao.readByEmail(email);
                if (user == null){
                    System.out.println("Taki użytkownik nie istnieje! Spróbuj ponownie");
                } else {
                    Scanner scannerTwo = new Scanner(System.in);
                    System.out.println("Wpisz hasło");
                    if (BCrypt.checkpw(scannerTwo.nextLine(), user.getPassword())){
                        System.out.println("Brawo! Zalogowałeś się");
                        userIsLoggingIn = false;
                    } else {
                        System.out.println("Niepoprawne hasło! Zaloguj się ponownie");
                    }
                }
            } else {
                break;
            }
        }

        if (!(userIsLoggingIn)) {
            String[] user = {email};
            UserHub.main(user);
        }
    }



}
