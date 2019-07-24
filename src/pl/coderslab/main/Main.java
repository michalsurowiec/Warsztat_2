package pl.coderslab.main;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.User;
import pl.coderslab.user.UserHub;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean userIsLoggingIn = true;
        User user = new User();
        while (userIsLoggingIn){
            String email = "";
            System.out.println("Podaj swój email lub wpisz quit jeżeli chcesz zrezygnować z logowania");
            Scanner scanner = new Scanner(System.in);
            email = scanner.next();
            if (!(email.equals("quit"))) {
                User userTest = UserDao.readByEmail(email);
                if (userTest == null){
                    System.out.println("Taki użytkownik nie istnieje! Spróbuj ponownie");
                } else {
                    Scanner scannerTwo = new Scanner(System.in);
                    System.out.println("Wpisz hasło");
                    if (BCrypt.checkpw(scannerTwo.nextLine(), userTest.getPassword())){
                        System.out.println("Brawo! Zalogowałeś się");
                        user = userTest;
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
            UserHub.main(user);
        }
    }



}
