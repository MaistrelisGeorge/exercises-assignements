package quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private List<User> users;
    private Map<String, List<Score>> scores;
    private Map<String, List<Score>> newScores;  // To track new scores

    public UserManager() {
        users = CSVReaderWriter.readUsers();
        scores = CSVReaderWriter.readScores();
        newScores = new HashMap<>();

        // Assign scores to users
        for (User user : users) {
            List<Score> userScores = scores.get(user.getUsername());
            if (userScores != null) {
                user.setScores(userScores);
            }
        }
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

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

    public void saveScores() {
        // Append new scores to the existing file
        CSVReaderWriter.writeScores(newScores);
        newScores.clear();  // Clear the new scores map after saving
    }

    public void addNewScore(User user, Score score) {
        String username = user.getUsername();
        newScores.computeIfAbsent(username, k -> new ArrayList<>()).add(score);
    }

    public User getMostActiveUser() {
        return users.stream()
                .max((u1, u2) -> Integer.compare(u1.getNumberOfQuizzesPlayed(), u2.getNumberOfQuizzesPlayed()))
                .orElse(null);
    }

    public User getBestUser() {
        return users.stream()
                .max((u1, u2) -> Double.compare(u1.getAverageScore(), u2.getAverageScore()))
                .orElse(null);
    }
}
