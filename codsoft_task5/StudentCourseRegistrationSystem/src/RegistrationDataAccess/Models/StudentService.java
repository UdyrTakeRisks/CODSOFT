package RegistrationDataAccess.Models;

import RegistrationBusinessLogic.CourseRegistrationController;
import RegistrationDataAccess.DatabaseConnection;
import RegistrationInterface.StudentView;

import java.sql.*;
import java.util.Objects;

public class StudentService {

    // CRUD Operations
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/sys";
    private final String username = "root";
    private final String password = "lifeistooshort2023";
    DatabaseConnection databaseConnection = new DatabaseConnection(jdbcUrl, username, password);
    Connection connection;
    private final Student student = new Student();
    public Student getStudent(){
        return student;
    }
    public void login() {
//        // Connect to mysql db
//        databaseConnection.connect();
//        connection = databaseConnection.getConnection();
        try {
            //insert into student (name, studentID) values(?,?);
            String sqlInsert = "INSERT INTO student (name, studentID) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getID());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
                StudentView.displayInsertionMsg();
            else
                StudentView.displayInsertionError();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        // Disconnect from database
//        databaseConnection.disconnect();
    }
    public void displayStudentCourses(){
        try {
            // select * from student where student.name = name AND student.id = id
            String sqlSelect = "SELECT * FROM student WHERE name = ? AND studentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                StudentView.showStudentCourses(resultSet.getString(2)
                                              ,resultSet.getInt(3)
                                              ,resultSet.getString(4));
            }else
                StudentView.displayNoStudentMsg();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet readRegisteredCourses(){
        try{
            //get registeredCourses
            String sqlSelect = "SELECT registeredCourses FROM student " +
                               "WHERE name = ? AND studentID = ?";
            PreparedStatement checkCourses = connection.prepareStatement(sqlSelect);
            checkCourses.setString(1, student.getName());
            checkCourses.setInt(2, student.getID());
            return checkCourses.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void registerStudentCourse(String registeredCourse){
        try {
            ResultSet resultSet = readRegisteredCourses();
            String updatedRegisteredCourses = " ";
            if(resultSet.next()) {
                if(resultSet.getString("registeredCourses") != null)
                    updatedRegisteredCourses = resultSet.getString("registeredCourses")
                        + ", " + registeredCourse;
                else
                    updatedRegisteredCourses = registeredCourse;
            }
            // data will be overwritten
            String sqlUpdate = "UPDATE student SET registeredCourses = ? " +
                               "WHERE name = ? AND studentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, updatedRegisteredCourses);
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getID());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
                StudentView.displayUpdateMsg();
            else
                StudentView.displayUpdateError();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropStudentCourse(String courseName){
        try {
            // DELETE FROM student
            //String sqlDelete = "DELETE FROM student WHERE name = ? AND id = ? AND registeredCourses = ?";
            String sqlRemove = "UPDATE student SET registeredCourses = REPLACE(registeredCourses, ?, '') " +
                               "WHERE name = ? AND studentID = ?";
            PreparedStatement removeStatement = connection.prepareStatement(sqlRemove);
            removeStatement.setString(1, courseName);
            removeStatement.setString(2, student.getName());
            removeStatement.setInt(3, student.getID());
            int rowAffected = removeStatement.executeUpdate();
            if(rowAffected > 0){
                StudentView.displayDroppedCourseMsg();
            }else
                StudentView.displayDroppedCourseIssue();
            removeStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkStudentExistence(){
        try{
            // Connect to mysql db
            databaseConnection.connect();
            connection = databaseConnection.getConnection();

            String sqlSelect = "SELECT name, studentID FROM student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while(resultSet.next()){
                if(Objects.equals(student.getName(), resultSet.getString("name")) && student.getID() == resultSet.getInt("studentID")){
                    return true;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean checkCourseRegisteredBefore(String course){
        try{
            String sqlSelect = "SELECT registeredCourses FROM student " +
                               "WHERE name = ? AND studentID = ?";
            PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
            selectStatement.setString(1, student.getName());
            selectStatement.setInt(2, student.getID());
            ResultSet resultSet = selectStatement.executeQuery();
            while(resultSet.next()){
                String registeredCourses = resultSet.getString("registeredCourses");
                if(registeredCourses == null){
                    break;
                }else if(registeredCourses.contains(course)){
                    return true;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
