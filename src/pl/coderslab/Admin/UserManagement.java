package pl.coderslab.Admin;

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
                System.out.println("[id: " + eachUser.getId() + ", Imię: " + eachUser.getName() + ", Hasło: " + eachUser.getPassword()
                + ", E-mail: " + eachUser.getEmail() + ", Zdolności: " + eachUser.getSkills() + ", Id Grupy: " + eachUser.getIdUserGroup()
                + "]");
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

            Scanner scannerOne = new Scanner(System.in);
            if (scannerOne.nextLine().equals("add")) {
                User addingUser = new User();

                System.out.println("Wpisz imię użytkownika.");
                Scanner scannerTwo = new Scanner(System.in);
                addingUser.setName(scannerTwo.nextLine());

                System.out.println("Wpisz hasło użytkownika.");
                Scanner scannerThree = new Scanner(System.in);
                addingUser.setPassword(scannerThree.nextLine());

                System.out.println("Wpisz e-mail użytkownika.");
                Scanner scannerFour = new Scanner(System.in);
                addingUser.setEmail(scannerFour.nextLine());

                System.out.println("Wpisz grupę użytkownika.");
                Scanner scannerFive = new Scanner(System.in);
                addingUser.setIdUserGroup(scannerFive.nextInt());

                try {
                    addingUser = UserDao.create(addingUser);
                    System.out.println("[id: " + addingUser.getId() + ", Imię: " + addingUser.getName() + ", Hasło: " + addingUser.getPassword()
                            + ", E-mail: " + addingUser.getEmail() + ", Zdolności: " + addingUser.getSkills() + ", Id Grupy: " + addingUser.getIdUserGroup()
                            + "]");
                } catch (NullPointerException npe) {
                    System.out.println("Nie udało się zapisać w bazie danych. Spróbuj ponownie z poprawnymi danymi.");
                }

            } else if (scannerOne.nextLine().equals("edit")) {
                System.out.println("Wpisz id użytkownika, którego chcesz edytować");
                Scanner scannerTwo = new Scanner(System.in);
                User editUser = UserDao.read(scannerTwo.nextInt());
                System.out.println("[id: " + editUser.getId() + ", Imię: " + editUser.getName() + ", Hasło: " + editUser.getPassword()
                        + ", E-mail: " + editUser.getEmail() + ", Zdolności: " + editUser.getSkills() + ", Id Grupy: " + editUser.getIdUserGroup()
                        + "]");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Zmieniono następujące dane użytkownika o id ").append(editUser.getId()).append(": ");

                System.out.println("Wpisz imię użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerThree = new Scanner(System.in);
                if (!(scannerThree.nextLine().equals("null"))) {
                    editUser.setName(scannerThree.nextLine());
                    stringBuilder.append("name, ");
                }

                System.out.println("Wpisz hasło użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerFour = new Scanner(System.in);
                if (!(scannerFour.nextLine().equals("null"))) {
                    editUser.setPassword(scannerFour.nextLine());
                    stringBuilder.append("password, ");
                }

                System.out.println("Wpisz e-mail użytkownika lub wpisz null, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerFive = new Scanner(System.in);
                if (!(scannerFive.nextLine().equals("null"))) {
                    editUser.setEmail(scannerFive.nextLine());
                    stringBuilder.append("email, ");
                }

                System.out.println("Wpisz grupę użytkownika lub wpisz 0, jeżeli nie chcesz nic zmieniać.");
                Scanner scannerSix = new Scanner(System.in);
                if (scannerSix.nextInt() != 0) {
                    editUser.setIdUserGroup(scannerSix.nextInt());
                    stringBuilder.append("idUserGroup");
                }

                UserDao.update(editUser);
                System.out.println(stringBuilder.toString());

            } else if (scannerOne.nextLine().equals("delete")) {

                System.out.println("Wprowadź id użytkownika, którego chcesz usunąć.");
                Scanner scannerTwo = new Scanner(System.in);
                int deleteId = scannerTwo.nextInt();
                UserDao.delete(deleteId);
                System.out.println("Usunąłeś użytkownika o id " + deleteId);

            } else if (scannerOne.nextLine().equals("quit")) {

                System.out.println("Właśnie opuszczasz program zarządzania użytkownikami.");
                programWorking = false;

            } else {
                wrongCommand = true;
            }
        }
    }

}
