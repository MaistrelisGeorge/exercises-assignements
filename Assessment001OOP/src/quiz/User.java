package quiz;

//User class to manage usernames,passwords,scores and their date/times

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Score> scores;

    //User class constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.scores = new ArrayList<>();
    }

    //return username
    public String getUsername() {
        return username;
    }

    //return password
    public String getPassword() {
        return password;
    }

    //check if password is right
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    //add score to user scorelist
    public void addScore(int score) {
        this.scores.add(new Score(score, LocalDateTime.now()));
    }

    //set user scores
    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    //return user scores
    public List<Score> getScores() {
        return scores;
    }

    //calculate and return user avrg score
    public double getAverageScore() {
        return scores.stream().mapToInt(Score::getScore).average().orElse(0.0);
    }

    //returns amount of quizzes played
    public int getNumberOfQuizzesPlayed() {
        return scores.size();
    }

    //display user scores w date/times
    public void displayScores() {
        for (Score score : scores) {
            System.out.println(score.getDatetime() + " - " + score.getScore());
        }
    }
}
