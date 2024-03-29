package pl.coderslab.main;

import pl.coderslab.admin.SolutionManagement;
import pl.coderslab.admin.ExerciseManagement;
import pl.coderslab.admin.UserGroupManagement;
import pl.coderslab.admin.UserManagement;

import java.util.Scanner;

public class AdminHub {

    public static void main(String[] args) {
        boolean isHubWorking = true;
        boolean wrongCommand = false;
        while (isHubWorking){

            if (wrongCommand){
                System.out.println("\nZła komenda! Spróbuj ponownie");
            }

            System.out.println("\nWitaj w panelu Admina! Poniżej masz listę modułów dostępnych do użycia.\n" +
                    "Wpisz odpowiednią cyfrę przy komendzie aby przejść do modułu lub wpisz quit aby opuścić panel admina.\n" +
                    "Lista komend:\n" +
                    "1 - przypisz ćwiczenie użytkownikowi lub sprawdź jego rozwiązania\n" +
                    "2 - zarządzaj ćwiczeniami\n" +
                    "3 - zarządzaj grupami użytkowników\n" +
                    "4 - zarządzaj użytkownikami\n" +
                    "quit - opuść program");

            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {
                case "1":
                    SolutionManagement.main();
                    break;
                case "2":
                    ExerciseManagement.main();
                    break;
                case "3":
                    UserGroupManagement.main();
                    break;
                case "4":
                    UserManagement.main();
                    break;
                case "quit":
                    System.out.println("Właśnie opuszczasz panel Admina. Do zobaczenia!");
                    isHubWorking = false;
                    break;
                default:
                    wrongCommand = true;
                    break;
            }

        }
    }

}
