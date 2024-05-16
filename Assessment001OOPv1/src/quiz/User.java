package quiz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Score> scores;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.scores = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addScore(int score) {
        this.scores.add(new Score(score, LocalDateTime.now()));
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return scores;
    }

    public double getAverageScore() {
        return scores.stream().mapToInt(Score::getScore).average().orElse(0.0);
    }

    public int getNumberOfQuizzesPlayed() {
        return scores.size();
    }

    public void displayScores() {
        for (Score score : scores) {
            System.out.println(score.getDatetime() + " - " + score.getScore());
        }
    }
}
