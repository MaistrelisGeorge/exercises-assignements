package Hangman_v1;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        User user;
        Hangman hangman=new Hangman();
        Scanner input=new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name=input.next();
        user=new User(name);
        do {
            hangman.newGame();
            do {
                System.out.println(hangman.getSecretWord());//we call the function to create the hidden secret word and display it
                System.out.print("Guess a letter: ");
                String guess = input.next();//user guess inputs
                hangman.play(guess);
                if (hangman.isFound()) {
                    System.out.println("Sweet victory!");
                    user.found();
                    break;
                } else if (hangman.isHanged()) {
                    System.out.println("Bitter defeat...");
                    user.hanged();
                    break;
                }
            } while(true);
            System.out.println("Play again champ? (y/n): ");
            String answer=input.next();
            if(answer=="n"){
                break;
            }
        }while(true);
        System.out.println(user.getFinalScore());
    }
}



