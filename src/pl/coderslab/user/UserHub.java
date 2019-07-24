package pl.coderslab.user;

import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.User;
import pl.coderslab.user.AddingSolution;

import java.util.Scanner;

public class UserHub {

    public static void main(User user) {

        boolean isHubWorking = true;
        boolean wrongCommand = false;
        while (isHubWorking) {

            if (wrongCommand){
                System.out.println("\nZła komenda! Spróbuj ponownie");
            }

            System.out.println("Witaj, " + user.getUsername() + "!\n" +
                    "Wybierz odpowiednią komendę z poniższej listy:\n" +
                    "1 - przejdź do modułu dodawania rozwiązań\n" +
                    "logout - wyloguj się");

            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()){
                case "1":
                    AddingSolution.main(user);
                    break;
                case "logout":
                    isHubWorking = false;
                    System.out.println("Żegnaj " + user.getUsername() + "! Do zobaczenia następnym razem.");
                    break;
                default:
                    wrongCommand = true;
                    break;
            }
        }

    }

}
