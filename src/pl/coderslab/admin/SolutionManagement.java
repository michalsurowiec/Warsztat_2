package pl.coderslab.admin;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.Exercise;
import pl.coderslab.plain.Solution;
import pl.coderslab.plain.User;

import java.util.Scanner;

public class SolutionManagement {

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
            System.out.println("edit - przypisz zadanie do użytkownika");
            System.out.println("view - wyświetl zadania konkretnego użytkownika");
            System.out.println("rate - oceń wypełnione zadania");
            System.out.println("delete - usuń zadania konkretnego użytkownika");
            System.out.println("quit - wyjdż z programu przypisywania zadań do użytkowników");

            try {
                Scanner scannerOne = new Scanner(System.in);
                switch (scannerOne.nextLine()) {
                    case "add": {

                        User[] userTable = UserDao.findAll();
                        System.out.println();
                        for (User eachUser : userTable) {
                            System.out.println(eachUser.toString());
                        }

                        Solution addSolution = new Solution();
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

                        break;
                    }

                    case "edit": {
                        System.out.println();
                        for (User eachUser : UserDao.findAll()) {
                            System.out.println(eachUser.toString());
                        }
                        System.out.println("\nWprowadź id użytkownika, któremu chcesz zmodyfikować przypisane zadania.\n");
                        Scanner scannerTwo = new Scanner(System.in);
                        Solution[] solutionTable = SolutionDao.findAllByUserId(scannerTwo.nextInt());
                        for (Solution solutionEach : solutionTable) {
                            System.out.println(solutionEach.toString());
                        }

                        System.out.println("\nWpisz id rozwiązania, które chcesz edytować\n");
                        Scanner scannerThree = new Scanner(System.in);
                        int scannerThreeInt = scannerThree.nextInt();
                        Solution editSolution = new Solution();
                        for (Solution solutionEach : solutionTable){
                            if (solutionEach.getId() == scannerThreeInt){
                                editSolution = solutionEach;
                            }
                        }

                        if (editSolution.getId() == 0){
                            System.out.println("Nie ma takiego rozwiązania!");
                            break;
                        }
                        System.out.println(editSolution.toString() + "\n");

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Zmieniono następujące dane rozwiązania o id ").append(editSolution.getId()).append(": ");

                        for (Exercise exerciseEach : ExerciseDao.findAll()) {
                            System.out.println(exerciseEach.toString());
                        }
                        System.out.println("\nWpisz id ćwiczenia lub wpisz 0, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerFour = new Scanner(System.in);
                        int scannerFourInt = scannerFour.nextInt();
                        if (scannerFourInt != 0) {
                            editSolution.setId_exercise(scannerFourInt);
                            stringBuilder.append("id_exercise, ");
                        }

                        for (User userEach : UserDao.findAll()) {
                            System.out.println(userEach.toString());
                        }
                        System.out.println("Wpisz idl użytkownika lub wpisz 0, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerFive = new Scanner(System.in);
                        int scannerFiveInt = scannerFive.nextInt();
                        if (scannerFiveInt != 0) {
                            editSolution.setId_users(scannerFiveInt);
                            stringBuilder.append("id_user");
                        }

                        SolutionDao.update(editSolution);
                        System.out.println(stringBuilder.toString());

                        break;
                    }

                    case "view": {

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

                        break;
                    }

                    case "rate": {
                        Solution[] solutionTable = SolutionDao.findAllByNotNullDescriptionAndRateEqualZero();
                        for (Solution solutionEach : solutionTable) {
                            System.out.println(solutionEach.toString());
                        }
                        System.out.println("\nWprowadź id rozwiązania, które chcesz ocenić i skomentować\n");
                        Scanner scannerTwo = new Scanner(System.in);
                        int scannerTwoInt = scannerTwo.nextInt();
                        Solution ratedSolution = new Solution();
                        for (Solution solutionEach : solutionTable){
                            if (solutionEach.getId() == scannerTwoInt){
                                ratedSolution = solutionEach;
                            }
                        }

                        if (ratedSolution.getId() == 0){
                            System.out.println("Nie ma takiego rozwiązania!");
                            break;
                        }

                        boolean checkingRate = true;
                        while (checkingRate) {
                            System.out.println("Wpisz ocenę z zakresu od 2.0 do 5.0\n");
                            Scanner scannerThree = new Scanner(System.in);
                            double rate = scannerThree.nextDouble();
                            if (rate >= 2.0 && rate <= 5.0) {
                                ratedSolution.setRate(rate);
                                checkingRate = false;
                            } else {
                                System.out.println("Niepoprawna ocena! Wpisz poprawnie.\n");
                            }
                        }

                        System.out.println("Wpisz komentarz do oceny\n");
                        Scanner scannerThree = new Scanner(System.in);
                        ratedSolution.setCommentary(scannerThree.nextLine());

                        System.out.println(ratedSolution.toString());
                        SolutionDao.update(ratedSolution);
                        break;
                    }

                    case "delete": {
                        for (Solution solutionEach : SolutionDao.findAll()) {
                            System.out.println(solutionEach.toString());
                        }
                        System.out.println("\nWpisz id rozwiązania, które chcesz usunąć\n");
                        Scanner scannerThree = new Scanner(System.in);
                        SolutionDao.delete(scannerThree.nextInt());
                        break;
                    }
                    case "quit": {

                        System.out.println("Właśnie opuszczasz program przypisywania zadań do użytkowników.");
                        programWorking = false;

                        break;
                    }
                    default: {
                        wrongCommand = true;
                        break;
                    }
                }
            } catch (Exception e){
                System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            }
        }
    }

}
