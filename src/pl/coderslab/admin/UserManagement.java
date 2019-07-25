package pl.coderslab.admin;

import pl.coderslab.dao.UserDao;
import pl.coderslab.plain.User;

import java.util.Scanner;

public class UserManagement {

    public static void main() {
        boolean programWorking = true;
        boolean wrongCommand = false;
        while (programWorking) {

            //Wstęp programu, wyświetlający listę i pytający o akcję
            System.out.println("Witaj w programie zarządzania użytkownikami. Lista użytkowników:\n");
            User[] userTable = UserDao.findAll();
            for (User eachUser : userTable) {
                System.out.println(eachUser.toString());
            }

            if (wrongCommand) {
                System.out.println("\nWpisałeś złą komendę! Wpisz poprawną w konsoli według poniższego schematu.");
                wrongCommand = false;
            }
            System.out.println("\nWybierz jedną z możliwych akcji i wpisz w konsoli:");
            System.out.println("add - dodaj użytkownika do bazy danych");
            System.out.println("edit - edytuj dane konkretnego użytkownika");
            System.out.println("delete - usuń użytkownika z bazy danych");
            System.out.println("quit - wyjdż z programu zarządzania użytkownikami");

            try {
                Scanner scannerOne = new Scanner(System.in);
                switch (scannerOne.nextLine()) {
                    case "add": {
                        User addingUser = new User();

                        System.out.println("Wpisz imię użytkownika.");
                        Scanner scannerTwo = new Scanner(System.in);
                        addingUser.setUsername(scannerTwo.nextLine());

                        System.out.println("Wpisz hasło użytkownika.");
                        Scanner scannerThree = new Scanner(System.in);
                        addingUser.hashPassword(scannerThree.nextLine());

                        System.out.println("Wpisz e-mail użytkownika.");
                        Scanner scannerFour = new Scanner(System.in);
                        addingUser.setEmail(scannerFour.nextLine());

                        System.out.println("Wpisz umiejętności użytkownika.");
                        Scanner scannerSix = new Scanner(System.in);
                        addingUser.setSkills(scannerSix.nextLine());

                        System.out.println("Wpisz grupę użytkownika.");
                        Scanner scannerFive = new Scanner(System.in);
                        addingUser.setIdUserGroup(scannerFive.nextInt());

                        try {
                            addingUser = UserDao.create(addingUser);
                            System.out.println(addingUser.toString());
                        } catch (NullPointerException npe) {
                            System.out.println("Nie udało się zapisać w bazie danych. Spróbuj ponownie z poprawnymi danymi.");
                        }

                        break;
                    }
                    case "edit": {
                        System.out.println("Wpisz id użytkownika, którego chcesz edytować");
                        Scanner scannerTwo = new Scanner(System.in);
                        User editUser = UserDao.read(scannerTwo.nextInt());
                        System.out.println(editUser.toString());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Zmieniono następujące dane użytkownika o id ").append(editUser.getId()).append(": ");

                        System.out.println("Wpisz imię użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerThree = new Scanner(System.in);
                        String scannerThreeText = scannerThree.nextLine();
                        if (!(scannerThreeText.equals("null"))) {
                            editUser.setUsername(scannerThreeText);
                            stringBuilder.append("name, ");
                        }

                        System.out.println("Wpisz hasło użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerFour = new Scanner(System.in);
                        String scannerFourText = scannerFour.nextLine();
                        if (!(scannerFourText.equals("null"))) {
                            editUser.hashPassword(scannerFourText);
                            stringBuilder.append("password, ");
                        }

                        System.out.println("Wpisz e-mail użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerFive = new Scanner(System.in);
                        String scannerFiveText = scannerFive.nextLine();
                        if (!(scannerFiveText.equals("null"))) {
                            editUser.setEmail(scannerFiveText);
                            stringBuilder.append("email, ");
                        }

                        System.out.println("Wpisz umiejętności użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerSix = new Scanner(System.in);
                        String scannerSixText = scannerSix.nextLine();
                        if (!(scannerSixText.equals("null"))) {
                            editUser.setSkills(scannerSixText);
                            stringBuilder.append("skills, ");
                        }

                        System.out.println("Wpisz grupę użytkownika lub wpisz 0, jeżeli nie chcesz nic zmieniać.");
                        Scanner scannerSeven = new Scanner(System.in);
                        int scannerSevenInt = scannerSeven.nextInt();
                        if (scannerSevenInt != 0) {
                            editUser.setIdUserGroup(scannerSevenInt);
                            stringBuilder.append("idUserGroup");
                        }

                        UserDao.update(editUser);
                        System.out.println(stringBuilder.toString());

                        break;
                    }
                    case "delete": {

                        System.out.println("Wprowadź id użytkownika, którego chcesz usunąć.");
                        Scanner scannerTwo = new Scanner(System.in);
                        int deleteId = scannerTwo.nextInt();
                        UserDao.delete(deleteId);
                        System.out.println("Usunąłeś użytkownika o id " + deleteId);

                        break;
                    }
                    case "quit":

                        System.out.println("Właśnie opuszczasz program zarządzania użytkownikami.");
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
