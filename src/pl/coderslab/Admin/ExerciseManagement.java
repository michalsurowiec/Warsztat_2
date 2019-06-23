package pl.coderslab.Admin;

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
                System.out.println("[id: " + eachExercise.getId() + ", tytuł: " + eachExercise.getTitle() + ", opis: "
                + eachExercise.getDescription() + " ]");
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

            Scanner scannerOne = new Scanner(System.in);
            if (scannerOne.nextLine().equals("add")) {
                Exercise addingExercise = new Exercise();

                System.out.println("Wpisz tytuł zadania.");
                Scanner scannerTwo = new Scanner(System.in);
                addingExercise.setTitle(scannerTwo.nextLine());

                System.out.println("Wpisz opis zadania.");
                Scanner scannerThree = new Scanner(System.in);
                addingExercise.setDescription(scannerThree.nextLine());

                try {
                    addingExercise = ExerciseDao.create(addingExercise);
                    System.out.println("[id: " + addingExercise.getId() + ", tytuł: " + addingExercise.getTitle() + ", opis: "
                            + addingExercise.getDescription() + " ]");
                } catch (NullPointerException npe) {
                    System.out.println("Nie udało się zapisać w bazie danych. Spróbuj ponownie z poprawnymi danymi.");
                }

            } else if (scannerOne.nextLine().equals("edit")) {
                System.out.println("Wpisz id zadania, które chcesz edytować");
                Scanner scannerTwo = new Scanner(System.in);
                Exercise editExercise = ExerciseDao.read(scannerTwo.nextInt());
                System.out.println("[id: " + editExercise.getId() + ", tytuł: " + editExercise.getTitle() + ", opis: "
                        + editExercise.getDescription() + " ]");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Zmieniono następujące dane zadania o id ").append(editExercise.getId()).append(": ");

                System.out.println("Wpisz tytuł zadania lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerThree = new Scanner(System.in);
                if (!(scannerThree.nextLine().equals("null"))) {
                    editExercise.setTitle(scannerThree.nextLine());
                    stringBuilder.append("title, ");
                }

                System.out.println("Wpisz opis zadania lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerFour = new Scanner(System.in);
                if (!(scannerFour.nextLine().equals("null"))) {
                    editExercise.setDescription(scannerFour.nextLine());
                    stringBuilder.append("description");
                }

                ExerciseDao.update(editExercise);
                System.out.println(stringBuilder.toString());

            } else if (scannerOne.nextLine().equals("delete")) {

                System.out.println("Wprowadź id zadania, które chcesz usunąć.");
                Scanner scannerTwo = new Scanner(System.in);
                int deleteId = scannerTwo.nextInt();
                ExerciseDao.delete(deleteId);
                System.out.println("Usunąłeś zadanie o id " + deleteId);

            } else if (scannerOne.nextLine().equals("quit")) {
                System.out.println("Właśnie opuszczasz program zarządzania zadaniami.");
                programWorking = false;

            } else {
                wrongCommand = true;
            }
        }
    }

}
