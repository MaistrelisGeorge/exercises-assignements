package quiz;

//CSVReaderWriter class to read and write username/password info in one csv and username/score/date on another

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReaderWriter {
    private static final String USER_FILE = "C:\\Users\\Abras\\Desktop\\users.csv"; //saves to my desktop, must change for another user
    private static final String SCORE_FILE = "C:\\Users\\Abras\\Desktop\\scores.csv"; //same as above
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //reads user/pass data from csv
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
            System.out.println("User file not found, new user list created!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    //write user/pass data to csv
    public static void writeUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //read score data from csv
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
            System.out.println("Score file not found, new score list created!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    //writes scores to csv in append mode
    //I had to do it to append properly whereas the user/pass csv worked without needing that, don't know why
    public static void writeScores(Map<String, List<Score>> scores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE, true))) {  //opens in append mode
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
