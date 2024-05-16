package quiz;

//Score class to manage score with date and time

import java.time.LocalDateTime;

public class Score {
    private int score;
    private LocalDateTime datetime;

    //Score class constructor
    public Score(int score, LocalDateTime datetime) {
        this.score = score;
        this.datetime = datetime;
    }

    //returns score
    public int getScore() {
        return score;
    }

    //returns date and time
    public LocalDateTime getDatetime() {
        return datetime;
    }
}
