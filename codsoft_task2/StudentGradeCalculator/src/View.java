public class View {
    // user interface
    public static void studentMenu(){
        System.out.print("""
                           Choose:
                           1- Calculate Total Marks
                           2- Calculate Average Percentage
                           3- Calculate Overall Grade
                           4- Show All Student Details
                           5- EXIT Student Menu
                           
                           Choice:\s""");
    }
    public static void userMenu(){
        System.out.print("""
                           Choose:
                           1- Proceed to Grade Calculator
                           2- Show All Student Grades Log
                           3- About the Student Grade Calculator
                           4- EXIT Program
                           
                           Choice:\s""");
    }
    public static void inputName(){
        System.out.print("Enter Your Full Name: ");
    }

    public static void inputID(){
        System.out.print("Enter Your ID: ");
    }
    public static void inputMarks(int subjectCounter){
        System.out.print("Enter Your Mark In Subject("+subjectCounter+"): ");
    }
    public static void inputNumOfSubjects(){
        System.out.print("Enter Your Number Of Subjects: ");
    }
    public static void displayWarningMsg(){
        System.out.println("You entered a wrong number, please enter a number from range(0-100)");
    }
    public static void displayErrorMsg(){
        System.out.println("You entered an invalid number, try again");
    }
    public static void displayAbout(){
        System.out.println("""
                ------------------------------------------------------
                A Grade Calculator for students that calculates
                grades per subject and overall grade for all subjects,
                it also stores student data in a log file.
                ------------------------------------------------------""");
    }
    public static void displayThanksMsg(){
        System.out.println("Thank you for using our program");
    }
    public static void showTotalMarks(int totalMarks){
        System.out.println("Your Total Marks is: " + totalMarks);
    }
    public static void showAveragePercentage(String averagePercentage){

        System.out.println("Your Average Percentage is: " + averagePercentage);
    }
    public static void overallGradeChoice(int overallGradeChoice){
        switch (overallGradeChoice) {
            case 1 -> System.out.println("Your Overall Grade is: A");
            case 2 -> System.out.println("Your Overall Grade is: B");
            case 3 -> System.out.println("Your Overall Grade is: C");
            case 4 -> System.out.println("Your Overall Grade is: D");
            case 5 -> System.out.println("Your Overall Grade is: F");
        }
    }
    public static void gradePerSubjectChoice(int gradeChoice){
        switch (gradeChoice) {
            case 1 -> System.out.println("Your Grade is: A+");
            case 2 -> System.out.println("Your Grade is: A");
            case 3 -> System.out.println("Your Grade is: B+");
            case 4 -> System.out.println("Your Grade is: B");
            case 5 -> System.out.println("Your Grade is: C+");
            case 6 -> System.out.println("Your Grade is: C");
            case 7 -> System.out.println("Your Grade is: D+");
            case 8 -> System.out.println("Your Grade is: D");
            case 9 -> System.out.println("Your Grade is: F");
        }
    }
    public static void displayContentsFromFile(String elements){
        System.out.println(elements);
    }
    public static void displayNoStudents(){
        System.out.println("No Students Logged Yet!");
    }
    public static void displayMarksError(){
        System.out.println("You Should Calculate Your Total Marks In Order To Show\n" +
                           "Your Average Percentage, Please Press 1");
    }
    public static void displayGradeError(){
        System.out.println("You Should Calculate Your Total Marks and Average Percentage In Order To Show\n" +
                "Your Overall Grade, Please Press 1 then 2");
    }
    public static void displayStudentData(String name, int ID, int numSubjects){
        System.out.println("Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("Number Of Subjects: " + numSubjects);
    }
    public static void inputPassword(){
        System.out.print("Enter Password: ");
    }
    public static void passwordLimitExceeded(){
        System.out.println("Sorry, You Have Exceeded The Limit Of Trials");
    }
    public static void displayWrongPasswordMsg(){
        System.out.println("Wrong Password, Try Again");
    }
}
