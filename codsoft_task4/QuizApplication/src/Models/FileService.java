package Models;

import Controllers.QuizController;
import View.QuizView;

import java.io.*;
import java.util.Arrays;

public class FileService {

    private static final File fileName = new File("Results.txt");
    private static final File quizFile = new File("Quiz.txt");
    public static void writeResultsToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
            writer.write("Name: " + QuizModel.Name + "\n");
            writer.write("Number of Questions: " + QuizModel.numberOfQuestions + "\n");
            writer.write("Final Quiz Score: " + QuizModel.scoreCalculation() + "/" + QuizModel.numberOfQuestions + "\n");
            writer.write("Quiz Summary:\n\n");
            for(int i = 0; i< QuizModel.numberOfQuestions; i++) {
                if (QuizModel.correctAnswers.get(i).equals(QuizModel.QuizTakerAnswers.get(i))) {
                    writer.write("Question("+(i+1)+"): " + QuizModel.Questions.get(i) + "\n");
                    writer.write("Choices: " + QuizModel.Choices.get(i) + "\n");
                    writer.write("Your Answer is: " + QuizModel.QuizTakerAnswers.get(i) + " (Correct) \n");
                    writer.newLine();
                }else{
                    writer.write("Question("+(i+1)+"): " + QuizModel.Questions.get(i) + "\n");
                    writer.write("Choices: " + QuizModel.Choices.get(i) + "\n");
                    writer.write("Your Answer is: " + QuizModel.QuizTakerAnswers.get(i) + " (Incorrect) \n");
                    writer.write("The Correct Answer is: " + QuizModel.correctAnswers.get(i) + "\n");
                    writer.newLine();
                }
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void readResultsFromFile(){
        // display user results and quiz summary
        try {
            if(fileName.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    QuizView.displayResults(line);
                }
            }else
                QuizView.displayNoFileMsg();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void writeQuizToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(quizFile));
            writer.write("Number of Questions: " + QuizModel.numberOfQuestions + "\n\n");
            for(int i = 0; i < QuizModel.numberOfQuestions; i++){
                writer.write("Question("+(i+1)+"): " + QuizModel.Questions.get(i) + "\n");
                writer.write("Choices("+(i+1)+"): " + QuizModel.Choices.get(i) + "\n");
                writer.write("The Correct Answer: " + QuizModel.correctAnswers.get(i) + "\n\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static boolean readQuizFromFile(){
        try {
            if(quizFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(quizFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Number of Questions: ")) {
                        String[] parts = line.split(":");
                        String numString = parts[1].trim();
                        QuizModel.numberOfQuestions = Integer.parseInt(numString);
                    }
                    if (line.contains("Question(")) {
                        String[] parts = line.split(": ");
                        String Question = parts[1].trim();
                        QuizModel.Questions.add(Question);
                    }
                    if (line.contains("Choices(")) {
                        String[] parts = line.split(": ");
                        String Choices = parts[1].trim();
                        QuizModel.Choices.add(Choices);
                    }
                    if(line.contains("The Correct Answer: ")){
                        String[] parts = line.split(":");
                        String correctAnswer = parts[1].trim();
                        QuizModel.correctAnswers.add(correctAnswer);
                    }
                }
                return true;
            }else
                QuizView.displayNoQuizFileMsg();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
