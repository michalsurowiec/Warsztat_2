package pl.coderslab.user;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.plain.Exercise;
import pl.coderslab.plain.Solution;
import pl.coderslab.plain.User;

import java.util.Scanner;

public class AddingSolution {

    public static void main(User user) {

        boolean programWorking = true;
        boolean wrongCommand = false;

        while (programWorking) {

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }

            System.out.println();

            for(Exercise exerciseEach : ExerciseDao.findAllByUserId(user.getId())){
                System.out.println(exerciseEach.toString());
            }

            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj rozwiązanie do ćwiczenia"); //wyświetlić listę ćwiczeń które nie zostały rozwiązane
            System.out.println("view - pokaż wszystkie dodane rozwiązania");
            System.out.println("edit - edytuj rozwiązanie");
            System.out.println("quit - wyjdż z modułu zarządzania rozwiązaniami\n");

            try {
                Scanner scannerOne = new Scanner(System.in);
                switch (scannerOne.nextLine()) {
                    case "add": {
                        for (Exercise exerciseEach : ExerciseDao.findAllByUserIdWithEmptySolution(user.getId())) {
                            System.out.println(exerciseEach.toString());
                        }
                        System.out.println("\nWpisz id zadania, do którego chcesz dodać rozwiązanie\n");
                        Scanner scannerTwo = new Scanner(System.in);
                        Solution solution = SolutionDao.readByUserIdAndExerciseId(user.getId(), scannerTwo.nextInt());
                        System.out.println("\nWpisz rozwiązanie\n");
                        Scanner scannerThree = new Scanner(System.in);
                        solution.setDescription(scannerThree.nextLine());
                        SolutionDao.update(solution);
                        break;
                    }
                    case "view": {
                        for (Solution solutionEach : SolutionDao.findAllByUserId(user.getId())) {
                            if (solutionEach.getDescription() != null) {
                                System.out.println(solutionEach.toString());
                            }
                        }
                        break;
                    }
                    case "edit": {
                        for (Solution solutionEach : SolutionDao.findAllByUserId(user.getId())) {
                            if (solutionEach.getDescription() != null) {
                                System.out.println(solutionEach.toString());
                            }
                        }
                        System.out.println("\nWpisz id rozwiązania, które chcesz edytować\n");
                        Scanner scannerTwo = new Scanner(System.in);
                        Solution editSolution = SolutionDao.read(scannerTwo.nextInt());
                        System.out.println(editSolution.toString());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Zmieniono następujące dane rozwiązania o id ").append(editSolution.getId()).append(": ");

                        System.out.println("\nWpisz treść rozwiązania lub wpisz null, jeżeli nie chcesz nic zmieniać.\n");
                        Scanner scannerThree = new Scanner(System.in);
                        String scannerThreeText = scannerThree.nextLine();
                        if (!(scannerThreeText.equals("null"))) {
                            editSolution.setDescription(scannerThreeText);
                            stringBuilder.append("description, ");
                        }

                        SolutionDao.update(editSolution);
                        System.out.println(stringBuilder.toString());

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
            } catch (Exception e){
                System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            }
        }
    }

}
