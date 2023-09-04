import java.util.Scanner;

public class View {
    //create a user menu display
    public static void displayUserMenu(){
        System.out.println("\nChoose: ");
        System.out.println("""
                1- Play the Game
                2- Show the Winners and Scores
                3- About the Game
                4- Exit
                """);
        System.out.print("Choice: ");
    }
    public static void displayExitMessage(){
        System.out.println("\nThanks For Playing!");
    }
    public static void displayErrorMessage(){
        System.out.println("\nYou Entered an Invalid Number, " +
                           "Please Try Again.");
    }
    public static void displayPlayerPrompt(){
        System.out.print("Enter Your Guessing Number: ");
    }
    public static void displayCorrectMsg(String playerName){
        System.out.println();
        System.out.println(playerName + ", You Guessed it Correct!");
    }
    public static void displayLowMsg(String playerName){
        System.out.println(playerName + ", Your Number is Too Low!\n");
    }
    public static void displayHighMsg(String playerName){
        System.out.println(playerName+ ", Your Number is Too High!\n");
    }
    public static void enterPlayerName(){
        System.out.print("Enter Your Name: ");
    }
    public static void displayAttempts(String playerName, int attemptsNum){
        System.out.println();
        System.out.println(playerName + ", You have " + attemptsNum + " Attempt(s) to go!");
    }
    public static void displayExceedMsg(String playerName){
        System.out.println("Sorry " + playerName + ", You have exceeded your attempts");
    }
    public static void displayFileContent(String content){
        System.out.println(content);
    }
    public static void displayNoWinners(){
        System.out.println("\nNo Winners Yet!");
    }
    public static void AboutTheGame(){
        System.out.println("""
                --------------------------------------------------------------------
                A simple game that generates a random number and the player tries\s
                to guess that number correctly in limited number of attempts.
                --------------------------------------------------------------------""");
    }
    public static void displayErrorGuess(){
        System.out.println("You Entered A Negative Number, Try Again");
    }
}
