package Kod;

import java.util.Scanner;

/**
 * @author Angel Cardenas Martinez anca8079
 */

public class Input{

    private Scanner input = new Scanner(System.in);

    public String getInput() {

        // print question
        System.out.print("Command?> ");
        String userInput = readString();

        return userInput;
    }
    //reads the input as a whole number.
    public int readInt(){
        int number = input.nextInt();
        input.nextLine();
        return number; 
    }
    //reads the input as a decimal.
    public double readDouble(){
        double number = input.nextDouble();
        input.nextLine();
        return number; 
    }
    //reads the input as text value.
    public String readString(){
        return input.nextLine();
    }
    //reads the input and formats it with the first letter being upper case and the rest being lower case.
    public String readFormatedString(){
        String name = input.nextLine();
        name = name.trim();
        if(name.replaceAll(" ", "").equals("")){//Based on Java documention and book examples.
            return name;
        }
        else{
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
        }
    }
}