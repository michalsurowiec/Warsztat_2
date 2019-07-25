package pl.coderslab.admin;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.plain.Exercise;

import java.util.Scanner;

public class ExerciseManagement {

    public static void main() {
        boolean programWorking = true;
        boolean wrongCommand = false;
        while (programWorking) {
            //Wstęp programu, wyświetlający listę i pytający o akcję
            System.out.println("Witaj w programie zarządzania zadaniami. Lista zadań:\n");
            Exercise[] exerciseTable = ExerciseDao.findAll();
            for (Exercise eachExercise : exerciseTable) {
                System.out.println(eachExercise.toString());
            }

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }
            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj zadanie do bazy danych");
            System.out.println("edit - edytuj dane konkretnego zadania");
            System.out.println("delete - usuń zadanie z bazy danych");
            System.out.println("quit - wyjdż z programu zarządzania zadaniami");

            try {
                Scanner scannerOne = new Scanner(System.in);
                switch (scannerOne.nextLine()) {
                    case "add": {
                        Exercise addingExercise = new Exercise();

                        System.out.println("Wpisz tytuł zadania.");
                        Scanner scannerTwo = new Scanner(System.in);
                        addingExercise.setTitle(scannerTwo.nextLine());

                        System.out.println("Wpisz opis zadania.");
                        Scanner scannerThree = new Scanner(System.in);
                        addingExercise.setDescription(scannerThree.nextLine());

                        try {
                            addingExercise = ExerciseDao.create(addingExercise);
                            System.out.println(addingExercise.toString());
                        } catch (NullPointerException npe) {
                            System.out.println("Nie udało się zapisać w bazie danych. Spróbuj ponownie z poprawnymi danymi.");
                        }

                        break;
                    }
                    case "edit": {
                        System.out.println("Wpisz id zadania, które chcesz edytować");
                        Scanner scannerTwo = new Scanner(System.in);
                        Exercise editExercise = ExerciseDao.read(scannerTwo.nextInt());
                        System.out.println(editExercise.toString());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Zmieniono następujące dane zadania o id ").append(editExercise.getId()).append(": ");

                        System.out.println("Wpisz tytuł zadania lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerThree = new Scanner(System.in);
                        String scannerThreeText = scannerThree.nextLine();
                        if (!(scannerThreeText.equals("null"))) {
                            editExercise.setTitle(scannerThreeText);
                            stringBuilder.append("title, ");
                        }

                        System.out.println("Wpisz opis zadania lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerFour = new Scanner(System.in);
                        String scannerFourText = scannerFour.nextLine();
                        if (!(scannerFourText.equals("null"))) {
                            editExercise.setDescription(scannerFourText);
                            stringBuilder.append("description");
                        }

                        ExerciseDao.update(editExercise);
                        System.out.println(stringBuilder.toString());

                        break;
                    }
                    case "delete": {

                        System.out.println("Wprowadź id zadania, które chcesz usunąć.");
                        Scanner scannerTwo = new Scanner(System.in);
                        int deleteId = scannerTwo.nextInt();
                        ExerciseDao.delete(deleteId);
                        System.out.println("Usunąłeś zadanie o id " + deleteId);

                        break;
                    }
                    case "quit":
                        System.out.println("Właśnie opuszczasz program zarządzania zadaniami.");
                        programWorking = false;

                        break;
                    default:
                        wrongCommand = true;
                        break;
                }
            } catch (Exception e){
                System.out.println("Wystąpił błąd! Upewnij się, że poprawnie wpisujesz dane.");
            }
        }
    }

}
