import java.util.Scanner;

public class Client {
    // MVC Pattern
    static Scanner input;
    public static Controller controller = new Controller();
    public static void runGame(){
        int playerChoice;
        while(true) {
            View.displayUserMenu();
            playerChoice = input.nextInt();
            switch (playerChoice) {

                // play the game
                case 1 -> controller.playGame();

                // read winners from file
                case 2 -> controller.model.readFromFile();

                // About
                case 3 -> View.AboutTheGame();

                // Exit
                case 4 -> {
                    View.displayExitMessage();
                    System.exit(0);
                }

                default -> View.displayErrorMessage();
            }
        }
    }

    public static void main(String[] args) {
        runGame();
    }

    static {input = new Scanner(System.in);}
}