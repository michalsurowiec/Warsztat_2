package pl.coderslab.admin;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.plain.UserGroup;

import java.util.Scanner;

public class UserGroupManagement {

    public static void main() {
        boolean programWorking = true;
        boolean wrongCommand = false;
        while (programWorking) {
            //Wstęp programu, wyświetlający listę i pytający o akcję
            System.out.println("Witaj w programie zarządzania grupami. Lista grup:\n");
            UserGroup[] userGroupTable = UserGroupDao.findAll();
            for (UserGroup eachUserGroup : userGroupTable) {
                System.out.println(eachUserGroup.toString());
            }

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }
            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj grupę do bazy danych");
            System.out.println("edit - edytuj dane konkretnej grupy");
            System.out.println("delete - usuń grupę z bazy danych");
            System.out.println("quit - wyjdż z programu zarządzania grupami");

            Scanner scannerOne = new Scanner(System.in);
            switch (scannerOne.nextLine()) {
                case "add": {
                    UserGroup addingUserGroup = new UserGroup();

                    System.out.println("Wpisz nazwę grupy.");
                    Scanner scannerTwo = new Scanner(System.in);
                    addingUserGroup.setName(scannerTwo.nextLine());

                    try {
                        addingUserGroup = UserGroupDao.create(addingUserGroup);
                        System.out.println(addingUserGroup.toString());
                    } catch (NullPointerException npe) {
                        System.out.println("Nie udało się zapisać w bazie danych. Spróbuj ponownie z poprawnymi danymi.");
                    }

                    break;
                }
                case "edit": {
                    System.out.println("Wpisz id grupy, którą chcesz edytować");
                    Scanner scannerTwo = new Scanner(System.in);
                    UserGroup editUserGroup = UserGroupDao.read(scannerTwo.nextInt());
                    System.out.println(editUserGroup.toString());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Zmieniono następujące dane grupy o id ").append(editUserGroup.getId()).append(": ");

                    System.out.println("Wpisz nazwę grupy lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                    Scanner scannerThree = new Scanner(System.in);

                    if (!(scannerThree.nextLine().equals("null"))) {
                        editUserGroup.setName(scannerThree.nextLine());
                        stringBuilder.append("name");
                    }

                    UserGroupDao.update(editUserGroup);
                    System.out.println(stringBuilder.toString());

                    break;
                }
                case "delete": {

                    System.out.println("Wprowadź id grupy, którą chcesz usunąć.");
                    Scanner scannerTwo = new Scanner(System.in);
                    int deleteId = scannerTwo.nextInt();
                    UserGroupDao.delete(deleteId);
                    System.out.println("Usunąłeś grupę o id " + deleteId);

                    break;
                }
                case "quit":

                    System.out.println("Właśnie opuszczasz program zarządzania grupami.");
                    programWorking = false;

                    break;
                default:
                    wrongCommand = true;
                    break;
            }
        }
    }

}
