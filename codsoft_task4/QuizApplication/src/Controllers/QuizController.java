package Controllers;

import Models.FileService;
import Models.QuizModel;
import View.QuizView;

import java.util.Scanner;
import java.util.concurrent.*;

public class QuizController {
    // Controller (handles user input and updates the model)
    static Scanner input;
    public static boolean oneTimeMadeQuiz = false;
    public static boolean answerIsWritten = true;
    public static void takeQuizMakerDetails(){
        QuizView.displayNumOfQuestions();
        QuizModel.numberOfQuestions = input.nextInt();
        for(int i=0;i<QuizModel.numberOfQuestions;i++) {
            input.nextLine();
            QuizView.displayQuestions(i+1);
            QuizModel.Question = input.nextLine();
            input.nextLine();
            QuizModel.Questions.add(QuizModel.Question);
            QuizView.displayChoices(i+1);
            QuizModel.Choice = input.nextLine();
            input.nextLine();
            QuizModel.Choices.add(QuizModel.Choice);
            QuizView.displayCorrectAnswer();
            QuizModel.correctAnswer = input.next();
            input.nextLine();
            QuizModel.correctAnswers.add(QuizModel.correctAnswer);
        }
        //one time quiz = true;
        oneTimeMadeQuiz = true;
    }
    public static void takeQuizTakerDetails(){
        QuizView.displayNamePrompt();
        input.nextLine();
        QuizModel.Name = input.nextLine();
    }
    public static void takeQuizTakerAnswers() {
        QuizView.showNumberOfQuestions(QuizModel.numberOfQuestions);
        for (int i = 0; i < QuizModel.numberOfQuestions; i++) {
            Scanner scanner = new Scanner(System.in);
            // Create a CountDownLatch with a count of 1
            CountDownLatch latch = new CountDownLatch(1);
            // Timer
            int timerDurationSeconds = 30;
            // Display Question(i):
            QuizView.showQuestions(i);
            // Display Choices(i):
            QuizView.showChoices(i);
            // Start the timer thread
            TimerThread timerThread = new TimerThread(timerDurationSeconds, i, latch);
            timerThread.start();
            latch.countDown();
            if(timerThread.getSeconds() > 0) {
                //Quiz taker answered before time is up
                QuizModel.QuizTakerAnswer = scanner.next().trim().toLowerCase();
                if(answerIsWritten) {
                    System.out.println(QuizModel.QuizTakerAnswer); //debug
                    QuizModel.QuizTakerAnswers.add(QuizModel.QuizTakerAnswer);
                }
                answerIsWritten = true;
                // Stop the timer
                timerThread.stopTimer();
            }
        }
    }
    public static void launchApp(){
        int choice;
        while(true) {
            QuizView.appMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1 -> QuizModel.makeQuiz();
                case 2 -> QuizModel.takeQuiz();
                case 3 -> QuizView.aboutTheApp();
                case 4 -> FileService.readResultsFromFile();
                case 5 -> System.exit(0);
            }
        }
    }
    static{input = new Scanner(System.in);}
}
