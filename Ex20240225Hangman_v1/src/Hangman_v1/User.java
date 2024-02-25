package Hangman_v1;

public class User {
    //init variables
    private String name;
    private int found_times;
    private int hanged_times;

    //constructor
    public User(String name){
        this.name=name;
        this.found_times=0;
        this.hanged_times=0;
    }
    //function to count losses
    public void hanged(){
        hanged_times++;
    }
    //function to count victories
    public void found(){
        found_times++;
    }
    public String getFinalScore(){
        return this.name+"Victory!: "+found_times+"Defeat...: "+hanged_times;
    }
}
