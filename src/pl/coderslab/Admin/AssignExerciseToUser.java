package pl.coderslab.Admin;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.Exercise;
import pl.coderslab.plain.Solution;
import pl.coderslab.plain.User;

import java.util.Scanner;

public class AssignExerciseToUser {

    public static void main() {
        boolean programWorking = true;
        boolean wrongCommand = false;
        while (programWorking) {
            System.out.println("Witaj w programie przypisywania zadań do użytkowników.");

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }
            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - przypisz zadanie do użytkownika");
            System.out.println("view - wyświetl zadania konkretnego użytkownika");
            System.out.println("quit - wyjdż z programu przypisywania zadań do użytkowników");

            Scanner scannerOne = new Scanner(System.in);
            if (scannerOne.nextLine().equals("add")) {
                Solution addSolution = new Solution();

                User[] userTable = UserDao.findAll();
                System.out.println();
                for (User eachUser : userTable) {
                    System.out.println(eachUser.toString());
                }
                System.out.println("\nWprowadź id użytkownika, któremu chcesz przypisać zadanie.");
                Scanner scannerTwo = new Scanner(System.in);
                addSolution.setId_users(scannerTwo.nextInt());

                System.out.println();
                Exercise[] exerciseTable = ExerciseDao.findAll();
                for (Exercise eachExercise : exerciseTable) {
                    System.out.println(eachExercise.toString());
                }
                System.out.println("\nWprowadź id ćwiczenia, które chcesz użytkownikowi przypisać.");
                Scanner scannerThree = new Scanner(System.in);
                addSolution.setId_exercise(scannerThree.nextInt());

                SolutionDao.create(addSolution);

            } else if (scannerOne.nextLine().equals("view")) {

                User[] userTable = UserDao.findAll();
                System.out.println();
                for (User eachUser : userTable) {
                    System.out.println(eachUser.toString());
                }
                System.out.println("\nWprowadź id użytkownika, którego zadania chcesz zobaczyć.");
                Scanner scannerTwo = new Scanner(System.in);

                Solution[] solutionTable = SolutionDao.findAllByUserId(scannerTwo.nextInt());
                System.out.println();
                for (Solution eachSolution : solutionTable) {
                    System.out.println(eachSolution.toString());
                }
                System.out.println();

            } else if (scannerOne.nextLine().equals("quit")) {

                System.out.println("Właśnie opuszczasz program przypisywania zadań do użytkowników.");
                programWorking = false;

            } else {
                wrongCommand = true;
            }
        }
    }

}
