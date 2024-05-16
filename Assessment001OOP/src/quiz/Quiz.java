package quiz;

//Quiz class to generally manage the quiz session

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private int current;
    private User user;
    private UserManager userManager;

    //Quiz class constructor
    public Quiz(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        newGame();
    }

    //gets questions and starts new game
    public void newGame() {
        APIReader reader = new APIReader();
        questions = reader.getQuestionList();
        score = 0;
        current = 0;
    }

    //displays curretn question
    public void displayCurrent() {
        questions.get(current).display();
    }

    //checks answer and updates score
    public void checkAnswer(int ans) {
        if (questions.get(current).isCorrectAnswer(ans)) {
            score++;
        }
        current++;
    }

    //checks if game is over
    public boolean gameOver() {
        return current == 10;
    }

    //generally manages quiz loop
    public void play() {
        Scanner scanner = new Scanner(System.in);
        newGame();
        while (!gameOver()) {
            displayCurrent();
            System.out.print("ANSWER: ");
            int ans = scanner.nextInt();
            checkAnswer(ans);
        }
        System.out.println("SCORE: " + score);
        user.addScore(score);  //adds score to user at the end of the quiz
        userManager.addNewScore(user, new Score(score, LocalDateTime.now()));  //notifies usermgr about the new score
    }
}
