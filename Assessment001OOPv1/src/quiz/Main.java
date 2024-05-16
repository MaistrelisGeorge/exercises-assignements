package quiz;

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
                scanner.nextLine();  // consume newline
                menu.processInitialMenu(choice, scanner);
            } else {
                menu.displaySecondMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
                menu.processSecondMenu(choice, scanner);
            }
        }
    }
}
