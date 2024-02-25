import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //initialization of variables
        int N=0;
        int i=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        N = scanner.nextInt();
        for (i = 0; i < N; i++) {
            System.out.print("* ");
        }
    }
}