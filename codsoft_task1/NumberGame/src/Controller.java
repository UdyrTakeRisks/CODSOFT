import java.util.Random;
import java.util.Scanner;

public class Controller {
    static Scanner input;
    public Random random = new Random();
    public Model model = new Model();
    private int playerGuess, attempts = 0;
    public int generateRandomNumber(){
        return random.nextInt(1,100);
    }
    public Boolean compareNumbers(int randomNum, int playerNum, String playerName){
        if(randomNum == playerNum) {
            return true;
        }
        else if(playerNum < randomNum) {
            if(attempts < 10)
                View.displayLowMsg(playerName);
            return false;
        }
        else {
            if(attempts < 10)
                View.displayHighMsg(playerName);
            return false;
        }
    }
    public void playGame(){
        int randomNumber, attemptsCounter = 10;
        String playerName;
        attempts = 0;

        //Generate a random number
        randomNumber = generateRandomNumber();

        //User will enter his guess
        View.enterPlayerName();
        playerName = input.next();

        while (attempts < 10) {
            //Limit the no. of attempts to 10
            View.displayAttempts(playerName, attemptsCounter);
            attemptsCounter--;
            View.displayPlayerPrompt();
            playerGuess = input.nextInt();
            if(playerGuess < 0) {
                View.displayErrorGuess();
                attempts--;
                attemptsCounter++;
            }
            else {
                //Compare and Provide feedback to the user
                // if the player guessed it right just break!
                if (compareNumbers(randomNumber, playerGuess, playerName)) {
                    break;
                }
            }
            attempts++;
        }
        if(compareNumbers(randomNumber, playerGuess, playerName)) {
            //Add the correct guesser + score (based on rounds won) to a file
            View.displayCorrectMsg(playerName);
            model.writeToFile(playerName);
        }

        if(attempts == 10)
            View.displayExceedMsg(playerName);

    }

    static {input = new Scanner(System.in);}
}
