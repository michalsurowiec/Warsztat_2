package pl.coderslab.user;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.plain.Exercise;
import pl.coderslab.plain.User;

import java.util.Scanner;

//Przejrzeć od początku jeszcze raz i zrobić odpowiednio klasę
public class AddingSolution {

    public static void main(User user) {

        boolean programWorking = true;
        boolean wrongCommand = false;

        while (programWorking) {

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.\n");
                wrongCommand = false;
            }

            for(Exercise exerciseEach : ExerciseDao.findAllByUserId(user.getId())){
                System.out.println(exerciseEach.toString());
            }

            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj rozwiązanie do ćwiczenia"); //wyświetlić listę ćwiczeń które nie zostały rozwiązane
            System.out.println("view - pokaż wszystkie dodane rozwiązania");
            System.out.println("edit - edytuj rozwiązanie");
            System.out.println("delete - usuń rozwiązanie");
            System.out.println("quit - wyjdż z modułu zarządzania rozwiązaniami\n");

            Scanner scannerOne = new Scanner(System.in);
            switch (scannerOne.nextLine()) {
                case "add": {
                    for(Exercise exerciseEach : ExerciseDao.findAllByUserIdWithEmptySolution(user.getId())){
                        System.out.println(exerciseEach.toString());
                    }
                    
                    break;
                }
                case "view": {
                    break;
                }
                case "edit": {
                    break;
                }
                case "delete": {
                    break;
                }
                case "quit": {
                    System.out.println("\nWłaśnie opuszczasz program dodawania rozwiązań.");
                    programWorking = false;
                    break;
                }
                default: {
                    wrongCommand = true;
                    break;
                }
            }
        }
    }

}
