package quiz;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReaderWriter {
    private static final String USER_FILE = "C:\\Users\\Abras\\Desktop\\users.csv";
    private static final String SCORE_FILE = "C:\\Users\\Abras\\Desktop\\scores.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            // File not found, return an empty list
            System.out.println("User file not found, starting with an empty user list.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void writeUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, List<Score>> readScores() {
        Map<String, List<Score>> scores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                int score = Integer.parseInt(parts[1]);
                LocalDateTime datetime = LocalDateTime.parse(parts[2], formatter);
                scores.computeIfAbsent(username, k -> new ArrayList<>()).add(new Score(score, datetime));
            }
        } catch (FileNotFoundException e) {
            // File not found, return an empty map
            System.out.println("Score file not found, starting with an empty score list.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    public static void writeScores(Map<String, List<Score>> scores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE, true))) {  // Open in append mode
            for (Map.Entry<String, List<Score>> entry : scores.entrySet()) {
                for (Score score : entry.getValue()) {
                    writer.println(entry.getKey() + "," + score.getScore() + "," + score.getDatetime().format(formatter));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
