package quiz;

//APIReader class, fetches the quiz questions from API

import org.apache.commons.lang3.StringEscapeUtils; //needed for proper special character format
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class APIReader {
    //fetches questions from API
    public JSONArray getQuestions() {
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 429) {//I had 429 problems, it still does them but eventually works
                System.out.println("Too Many Requests - Please slow down your requests!");
                Thread.sleep(1000); //sleep for 1 second so i won't get too many tries error from API
                return getQuestions(); //retry the request
            }
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(is);
            JSONArray questions = (JSONArray) json.get("results");
            return questions;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //turns JSON questions to a list of questions
    public ArrayList<Question> getQuestionList() {
        ArrayList<Question> questions = new ArrayList<>();
        JSONArray json = getQuestions();
        if (json == null) {
            return questions;
        }
        for(int i = 0; i < json.size(); i++) {
            JSONObject obj = (JSONObject) json.get(i);
            String type = (String) obj.get("type");
            Question q;
            if(type.equals("boolean")) {
                q = new TrueFalse();
            }
            else {
                q = new MultipleChoice();
            }
            q.category = (String) obj.get("category");
            q.question = StringEscapeUtils.unescapeHtml4((String) obj.get("question"));
            JSONArray incorrect = (JSONArray) obj.get("incorrect_answers");
            String [] incorrectAnswers = new String[3];
            for(int j = 0; j < incorrect.size(); j++) {
                incorrectAnswers[j] = StringEscapeUtils.unescapeHtml4((String) incorrect.get(j));
            }
            q.setAnswers(StringEscapeUtils.unescapeHtml4((String) obj.get("correct_answer")), incorrectAnswers);
            questions.add(q);
        }
        return questions;
    }
}
