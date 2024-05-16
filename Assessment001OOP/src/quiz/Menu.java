package quiz;

import java.util.Scanner;

public class Menu {
    private UserManager userManager;
    private User currentUser;
    private Quiz quiz;

    public Menu(UserManager userManager) {
        this.userManager = userManager;
    }

    public void displayInitialMenu() {
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");
    }

    public void displaySecondMenu() {
        System.out.println("1) Play quiz");
        System.out.println("2) See scores");
        System.out.println("3) See most active player");
        System.out.println("4) See player with best average score");
        System.out.println("5) Exit");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void processInitialMenu(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                currentUser = userManager.login(username, password);
                if (currentUser == null) {
                    System.out.println("Invalid username or password.");
                }
                break;
            case 2:
                System.out.print("Username: ");
                username = scanner.nextLine();
                System.out.print("Password: ");
                password = scanner.nextLine();
                if (!userManager.register(username, password)) {
                    System.out.println("Username already exists.");
                }
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void processSecondMenu(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                quiz = new Quiz(currentUser, userManager);  // Pass both currentUser and userManager
                quiz.play();
                userManager.saveScores();  // Save scores after playing the quiz
                break;
            case 2:
                currentUser.displayScores();
                break;
            case 3:
                User mostActiveUser = userManager.getMostActiveUser();
                System.out.println("Most active player: " + mostActiveUser.getUsername());
                break;
            case 4:
                User bestUser = userManager.getBestUser();
                System.out.println("Player with best average score: " + bestUser.getUsername());
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
