package quiz;

//Main class with method to run the program

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Menu menu = new Menu(userManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (menu.getCurrentUser() == null) {
                menu.displayInitialMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                menu.processInitialMenu(choice, scanner);
            } else {
                menu.displaySecondMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                menu.processSecondMenu(choice, scanner);
            }
        }
    }
}
