import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Presenter {
    // MVP Pattern
    // presenter handles user input and uses Model and View
    static Scanner input;
    public static void takeStudentDetails(){
        //Take Student input -> Name and ID
        View.inputName();
        input.nextLine();
        Model.studentName = input.nextLine();
        View.inputID();
        Model.studentID = input.nextInt();
        View.inputNumOfSubjects();
        Model.numOfSubjects = input.nextInt();
        Model.studentMarks = new ArrayList<>(Model.numOfSubjects);
        for(int i=0;i<Model.numOfSubjects;i++){
            View.inputMarks(i+1);
            Model.studentMark = input.nextInt();
            //exception
            while(Model.studentMark < 0 || Model.studentMark > 100) {
                //display warning
                View.displayWarningMsg();
                View.inputMarks(i+1);
                Model.studentMark = input.nextInt();
            }
            Model.assignGradePerSubject();
            Model.studentMarks.add(Model.studentMark);
        }
    }
    public static void studentFunctions(){
        // use Model to do the main function of the program
        int studentChoice, counter = 0;
        while (true) {
            View.studentMenu();
            studentChoice = input.nextInt();
            if (studentChoice == 1) {
                //Show total marks
                while(counter < 1) {
                    Model.calculateTotalMarks();
                    counter++;
                }
                View.showTotalMarks(Model.totalMarks);
            }else if (studentChoice == 2)
                //show average percentage
                Model.calculateAveragePercentage();
            else if (studentChoice == 3)
                // show overall grade
                Model.assignOverallGrade();
            else if (studentChoice == 4)
                // show all
                Model.displayResults();
            else if (studentChoice == 5) {
                //save student data to a file
                Model.writeToFile();
                //reset Marks
                Model.totalMarks = 0;
                // exit from student menu
                break;
            }else
                View.displayErrorMsg();
        }
    }
    public static void showStudentGrades(){
        int password, counter = 0;
        while(counter < 3) {
            View.inputPassword();
            password = input.nextInt();
            if(password != 1234) {
                View.displayWrongPasswordMsg();
            }
            if(password == 1234) {
                Model.readFromFile(password);
                break;
            }
            counter++;
        }
        if(counter == 3)
            View.passwordLimitExceeded();
    }
    public static void runProgram(){
        int userChoice;
        while (true) {
            View.userMenu();
            userChoice = input.nextInt();
            switch (userChoice){
                case 1 -> {
                    takeStudentDetails();
                    studentFunctions();
                }
                //show all student grades log, needs a password, and you have only 3 trials
                case 2 -> showStudentGrades();
                // About
                case 3 -> View.displayAbout();
                case 4 -> {
                    View.displayThanksMsg();
                    System.exit(0);
                }
                default -> View.displayErrorMsg();
            }
        }
    }

    public static void main(String[] args) {
        runProgram();
    }

    static { input = new Scanner(System.in);}
}