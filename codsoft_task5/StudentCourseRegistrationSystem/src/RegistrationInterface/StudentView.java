package RegistrationInterface;

public class StudentView {
    public static void createMenu(){
        System.out.print("""
                Choose:
                1- Login
                2- Display Available Courses
                3- Register For a Course
                4- Drop a Course
                5- Display Your Registered Courses
                6- About the System
                7- Exit
                Choice:\s""");
    }
    public static void AboutTheSystem(){
        System.out.println("""
                -----------------------------------------------------------------
                A student course registration system that uses mysql sys database\s
                for storing students/courses information with some features like
                listing courses, registering courses, and dropping courses.\s
                ------------------------------------------------------------------
                """);
    }
    public static void inputName(){
        System.out.print("Enter Your Name: ");
    }
    public static void inputID(){
        System.out.print("Enter Your ID: ");
    }
    public static void displayErrorMsg(){
        System.out.println("You Should Login First to Register, Drop, or Display a Course!");
    }
    public static void displayInsertionMsg(){
        System.out.println("Record has been inserted, logged in successfully");
    }
    public static void displayInsertionError(){
        System.out.println("Error in inserting record");
    }
    public static void displayUpdateMsg(){
        System.out.println("Your Courses has been updated with a registered course");
    }
    public static void displayUpdateError(){
        System.out.println("Error in updating record");
    }
    public static void inputCourseID(){
        System.out.print("Enter the ID of the Course you want to Register: ");
    }
    public static void displayNoCourseIDMsg(){
        System.out.println("Wrong Course ID, please try again");
    }
    public static void displayNoCourseFound(){
        System.out.println("No Course Found With This Name!");
    }
    public static void insufficientCapacityMsg(){
        System.out.println("Sorry, The Course With It's Full Capacity!");
    }
    public static void displayCapacityUpdate(){
        System.out.println("Course Capacity has been updated!");
    }
    public static void displayCapacityUpdateIssue(){
        System.out.println("Error in updating capacity of the course");
    }
    public static void showStudentCourses(String name, int ID, String registeredCourses){
        System.out.println("Student Name: " + name
                         + " Student ID: "   + ID
                         + " Registered Courses: " + registeredCourses);
    }
    public static void displayNoStudentMsg(){
        System.out.println("No Student Name or ID Exists!");
    }
    public static void inputCourseName(){
        System.out.print("Enter the Name of the Course You want to Drop: ");
    }
    public static void displayDroppedCourseMsg(){
        System.out.println("You Dropped the Course");
    }
    public static void displayDroppedCourseIssue(){
        System.out.println("Sorry, Error Happened in Dropping Your Course, Check the Course Name");
    }
    public static void displayWelcomeMsg(String studentName){
        System.out.println("Welcome Again in the System, " + studentName);
    }
    public static void displayRegistrationIssue(){
        System.out.println("Sorry You Registered to this course before, Please Display your courses");
    }
    public static void NoCoursesFound(){
        System.out.println("No Courses Registered to Drop");
    }
}
