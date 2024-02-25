package Hangman_v1;

import java.util.Random;

public class Hangman {
    //init variables
    private String[]words={"airplane","university","elephant","mississippi","machinegun","australia","skyfall","tourist","passenger","electricity"};
    private String secret;
    private String correct_responses;
    private String wrong_responses;

    //constructor
    public Hangman(){
        this.secret="";
        this.correct_responses="";
        this.wrong_responses="";
    }
    //new game function
    public void newGame(){
        Random rnd=new Random();
        int pos=rnd.nextInt(this.words.length-1);//length is 10 but we want up to 9 bc counting starts at 0
        this.secret=this.words[pos];
        //re init the responses
        this.correct_responses="";
        this.wrong_responses="";
    }
    //function to create hidden secret word and reveal correct guesses
    public String getSecretWord(){
        String secret=""+this.secret.charAt(0);//we have to create an empty string first, then add
        for(int i=1;i<this.secret.length()-1;i++){
            String s=""+this.secret.charAt(i);
            if(this.correct_responses.contains(s)){
                secret+=s;
            }
            else{
                secret+="_";
            }
        }
        secret+=this.secret.charAt(this.secret.length()-1);
        return secret;
    }
    //function for filling the words using input from guess
    public void play(String guess){
        //we need to check that guess is just one char
        if(this.secret.contains(guess)){
            this.correct_responses+=guess;
        }
        else{
            this.wrong_responses+=guess;
        }
    }
    //function to check whether we lose the game
    public boolean isHanged(){
        return this.wrong_responses.length()==6;//6 wrong guesses limit
    }
    //function to check for victory
    public boolean isFound(){
        for(int i=1;i<this.secret.length()-1;i++){
            String s=""+this.secret.charAt(i);
            if(!this.correct_responses.contains(s)){
                return false;
            }
        }
        return true;
    }
}
