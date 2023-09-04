package View;

import Models.QuizModel;

public class QuizView {
    // View (user interface)

    public static void appMenu(){
        System.out.print("""
                \nChoose:
                1- Make A Quiz
                2- Take A Quiz
                3- About the App
                4- Display Result Screen
                5- Exit the App
                Choice:\s""");
    }
    public static void displayNamePrompt(){
        System.out.print("Type Your Name: ");
    }
    public static void displayNumOfQuestions(){
        System.out.print("How Many Questions: ");
    }
    public static void displayQuestions(int i){
        System.out.print("Write Question("+i+"): ");
    }
    public static void displayChoices(int i){
        System.out.print("Write Q("+i+") Choices: ");
    }
    public static void displayCorrectAnswer(){
        System.out.print("Write the Correct Answer: ");
    }
    public static void aboutTheApp(){
        System.out.println("""
                ---------------------------------------------------
                A Quiz Application With a Timer For Each Question.
                ---------------------------------------------------
                """);
    }
    public static void displayTimer(int i, int j){
        System.out.print("\rTimer For Question("+i+"): " + j + " Seconds, " + chooseAnswer());
    }
    public static void showQuestions(int i){
        System.out.println("Question("+(i+1)+"): " + QuizModel.Questions.get(i));
    }
    public static void showChoices(int i){
        System.out.println("Choices("+(i+1)+"): " + QuizModel.Choices.get(i));
    }
    public static String chooseAnswer(){
        return ("Choose the Correct Answer: ");
    }
    public static void showNumberOfQuestions(int numberOfQuestions){
        System.out.println("Number of Questions: " + numberOfQuestions);
    }
    public static void displayResults(String content){
        System.out.println(content);
    }
    public static void timeIsUp(int i){
        System.out.println("\nTime is up for Question("+i+")");
    }
    public static void displayNoFileMsg(){
        System.out.println("No Results For Quiz Takers Yet!");
    }
    public static void displayNoQuizFileMsg(){
        System.out.println("No Quiz Made Yet!");
    }
    public static void enterNext(){
        System.out.print("Enter Next or any key to Continue: ");
    }
    public static void sorryMsg(){
        System.out.println("Sorry, You Can Make One Quiz Only Per Run");
    }
}
