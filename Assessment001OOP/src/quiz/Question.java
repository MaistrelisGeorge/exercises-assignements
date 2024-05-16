package quiz;

//Question class to represent a generic question

public class Question {
    protected String category;
    protected String question;
    protected String [] answers;
    protected int correctIndex;

    //sets answers for questions to be overridden by subclasses
    public void setAnswers(String correct, String [] wrong) {
        // do nothing
    }

    //display the questions and the multiple choice or t/f
    public void display() {
        System.out.println("Q: " + question);
        int counter = 1;
        for(String answer : answers) {
            System.out.println(counter + ". " + answer);
            counter++;
        }
        //System.out.println(correctIndex); //hint for the correct answer for testing enable/disable as needed
    }

    //checks if the answer is right
    public boolean isCorrectAnswer(int ans) {
        return (ans-1) == correctIndex;
    }
}
