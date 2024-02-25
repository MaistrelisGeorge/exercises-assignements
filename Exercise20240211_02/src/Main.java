//importing libraries needed
import java.util.Random; //for random number generation

public class Main {
    public static void main(String[] args) {
        //initialization of variables
        int[] values=new int[10];
        Random rand=new Random();
        int sum=0,min=10,max=-1;

        //for loop
        for(int i=0;i<values.length;i++){
            values[i]=rand.nextInt(101);
            sum+=values[i];
            min=Math.min(min,values[i]);
            max=Math.max(max,values[i]);
        }
        double average=(double)sum/values.length;
        //output the results
        System.out.println("The maximum value is: "+max);
        System.out.println("The minimum value is: "+min);
        System.out.println("The sum is: "+sum);
        System.out.println("The average is: "+average);
    }
}