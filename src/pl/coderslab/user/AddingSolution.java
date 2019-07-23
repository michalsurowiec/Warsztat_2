package pl.coderslab.user;

import java.util.Scanner;

//Przejrzeć od początku jeszcze raz i zrobić odpowiednio klasę
public class AddingSolution {

    public static void main() {

        boolean programWorking = true;
        boolean wrongCommand = false;

        while (programWorking) {

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }

            System.out.println("Wybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj użytkownika do bazy danych");
            System.out.println("edit - edytuj dane konkretnego użytkownika");
            System.out.println("delete - usuń użytkownika z bazy danych");
            System.out.println("quit - wyjdż z programu zarządzania użytkownikami");

            Scanner scannerOne = new Scanner(System.in);
            if (scannerOne.nextLine().equals("add")) {

            } else if (scannerOne.nextLine().equals("view")) {

            } else if (scannerOne.nextLine().equals("quit")) {

                System.out.println("Właśnie opuszczasz program dodawania rozwiązań.");
                programWorking = false;

            } else {
                wrongCommand = true;
            }
        }
    }

}
