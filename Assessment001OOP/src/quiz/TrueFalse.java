package quiz;

//TrueFalse class to manage t/f questions

public class TrueFalse extends Question {

    //sets answers for t/f questions
    @Override
    public void setAnswers(String correct, String[] wrong) {
        String [] answers = {"True", "False"};
        if(correct.equals("True")) {
            correctIndex = 0;
        }
        else {
            correctIndex = 1;
        }
        this.answers = answers;
    }
}
