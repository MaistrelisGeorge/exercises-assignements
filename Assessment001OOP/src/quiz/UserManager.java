package quiz;

//UserManager class manages user authentication and score

import java.util.*;

public class UserManager {
    private List<User> users;
    private Map<String, List<Score>> scores;
    private Map<String, List<Score>> newScores;  //to track new scores

    public UserManager() {
        users = CSVReaderWriter.readUsers();
        scores = CSVReaderWriter.readScores();
        newScores = new HashMap<>();

        //to set scores for users
        for (User user : users) {
            List<Score> userScores = scores.get(user.getUsername());
            if (userScores != null) {
                user.setScores(userScores);
            }
        }
    }

    //user login w password
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

    //user registration ONLY if username doesn't exist
    public boolean register(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User newUser = new User(username, password);
        users.add(newUser);
        scores.put(username, newUser.getScores());
        CSVReaderWriter.writeUsers(users);
        return true;
    }

    //saves new scores to csv
    public void saveScores() {
        //appends new scores to csv
        CSVReaderWriter.writeScores(newScores);
        newScores.clear();
    }

    //adds new score for user
    public void addNewScore(User user, Score score) {
        String username = user.getUsername();
        newScores.computeIfAbsent(username, k -> new ArrayList<>()).add(score);
    }

    //finds user with most games then returns
    //intellj has a proposal for a replacement here, maybe try later
    public User getMostActiveUser() {
        return users.stream()
                .max((u1, u2) -> Integer.compare(u1.getNumberOfQuizzesPlayed(), u2.getNumberOfQuizzesPlayed()))
                .orElse(null);
    }

    //finds user with best avrg score then returns
    //intellj has a proposal for a replacement here, maybe try later
    public User getBestUser() {
        return users.stream()
                .max((u1, u2) -> Double.compare(u1.getAverageScore(), u2.getAverageScore()))
                .orElse(null);
    }
}
