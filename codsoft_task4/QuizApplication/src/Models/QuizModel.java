package Models;

import Controllers.QuizController;
import View.QuizView;

import java.util.ArrayList;
public class QuizModel {
    // Model (data and logic)
    public static String Name;
    public static int numberOfQuestions;
    public static String Question;
    public static String Choice;
    public static String correctAnswer;
    public static String QuizTakerAnswer;
    public static ArrayList<String> Questions = new ArrayList<>();
    public static ArrayList<String> Choices = new ArrayList<>();
    public static ArrayList<String> correctAnswers = new ArrayList<>();
    public static ArrayList<String> QuizTakerAnswers = new ArrayList<>();
    private static boolean QuizMade = false;
    public static void makeQuiz(){
        // make a quiz one time per run
        //if one time quiz == false
        if(!QuizController.oneTimeMadeQuiz) {
            QuizController.takeQuizMakerDetails();
            // write the quiz to a file .
            FileService.writeQuizToFile();
            QuizMade = true;
        } else
            QuizView.sorryMsg();
    }
    public static void takeQuiz(){
        QuizController.takeQuizTakerDetails();
        if(QuizMade){ //take the new quiz made
            QuizTakerAnswers.clear();
            QuizController.takeQuizTakerAnswers();
        }
        else{ //take the old quiz found in file
            // read the quiz from the file (default quiz)
            if(FileService.readQuizFromFile()) {
                QuizTakerAnswers.clear();
                QuizController.takeQuizTakerAnswers();
            }
        }
        // write the results in a file
        FileService.writeResultsToFile();
    }
    public static int scoreCalculation(){
        int quizScore = 0;
        for(int i=0;i<numberOfQuestions;i++){
            if(correctAnswers.get(i).equals(QuizTakerAnswers.get(i))){
                quizScore++;
            }
        }
        return quizScore; // write the score to the results file
    }
}
