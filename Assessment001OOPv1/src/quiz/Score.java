package quiz;

import java.time.LocalDateTime;

public class Score {
    private int score;
    private LocalDateTime datetime;

    public Score(int score, LocalDateTime datetime) {
        this.score = score;
        this.datetime = datetime;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }
}
