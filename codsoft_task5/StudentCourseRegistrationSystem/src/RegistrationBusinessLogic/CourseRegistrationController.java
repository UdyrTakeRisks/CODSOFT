package RegistrationBusinessLogic;

import RegistrationDataAccess.Models.CourseService;
import RegistrationDataAccess.Models.StudentService;
import RegistrationInterface.StudentView;

import java.util.Objects;
import java.util.Scanner;

public class CourseRegistrationController {
    static Scanner input;
    static StudentService studentService = new StudentService();
    static CourseService courseService = new CourseService();
    public static boolean loginFlag = false, registerFlag = false, dropFlag = false;
    public static void studentLogin(){
        StudentView.inputName();
        input.nextLine();
        studentService.getStudent().setName(input.nextLine());
        StudentView.inputID();
        studentService.getStudent().setID(input.nextInt());
        //Check if this student logged in before!
        if(studentService.checkStudentExistence())
            //The student is already stored in the database
            StudentView.displayWelcomeMsg(studentService.getStudent().getName());
        else
            studentService.login();
        // if success, login successfully
        loginFlag = true;
    }
    public static void displayAvailableCourses(){
        courseService.listAvailableCourses();
    }
    public static void registerCourse(){
        if(loginFlag) {
            courseService.listAvailableCourses();
            StudentView.inputCourseID();
            int courseID = input.nextInt();
            String course = courseService.chooseCourse(courseID);
            if(!Objects.equals(course, " ")){
                //check also if this student registered this course before or not
                if(studentService.checkCourseRegisteredBefore(course))
                    //the course has been registered already
                    StudentView.displayRegistrationIssue();
                else {
                    studentService.registerStudentCourse(course);
                    registerFlag = true;
                    //update the course capacity
                    courseService.updateCourseCapacity(course);
                }
            }else
                StudentView.displayNoCourseIDMsg();
        }else
            StudentView.displayErrorMsg();
    }
    public static void dropCourse(){
        if(loginFlag) {
            studentService.displayStudentCourses();
            StudentView.inputCourseName();
            input.nextLine();
            String courseName = input.nextLine();
            if(courseName != null) {
                studentService.dropStudentCourse(courseName);
                dropFlag = true;
                courseService.updateCourseCapacity(courseName);
            }
            else
                StudentView.NoCoursesFound();
        }
        else
            StudentView.displayErrorMsg();
    }
    public static void displayRegisteredCourses(){
        if(loginFlag)
            studentService.displayStudentCourses();
        else
            StudentView.displayErrorMsg();
    }

    public static void startSystem(){
        int studentChoice;
        while(true) {
            StudentView.createMenu();
            studentChoice = input.nextInt();
            switch (studentChoice) {
                case 1 -> studentLogin();
                case 2 -> displayAvailableCourses();
                case 3 -> registerCourse();
                case 4 -> dropCourse();
                case 5 -> displayRegisteredCourses();
                case 6 -> StudentView.AboutTheSystem();
                case 7 -> System.exit(0);
            }
        }
    }
    static {input = new Scanner(System.in);}
}
