package RegistrationDataAccess.Models;

import RegistrationBusinessLogic.CourseRegistrationController;
import RegistrationDataAccess.DatabaseConnection;
import RegistrationInterface.StudentView;

import java.sql.*;

public class CourseService {

    // CRUD Operations
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/sys";
    private final String username = "root";
    private final String password = "lifeistooshort2023";
    DatabaseConnection databaseConnection = new DatabaseConnection(jdbcUrl, username, password);
    Connection connection;
    public void listAvailableCourses() {
        // Connect to mysql db
        databaseConnection.connect();
        connection = databaseConnection.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM course");
            while(resultSet.next()) {
                System.out.println("ID: " + resultSet.getString(1)
                                 + " Course Code: " + resultSet.getString(2)
                                 + " Title: " + resultSet.getString(3)
                                 + " Description: " + resultSet.getString(4)
                                 + " Capacity: " + resultSet.getString(5)
                                 + " Schedule: " + resultSet.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        // Disconnect from database
//        databaseConnection.disconnect();
    }
    public String chooseCourse(int courseID){
        //Already db connected from course listing
        String courseTitle = " ";
        try {
            String sqlSelect = "SELECT title FROM course WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, courseID); // positional parameters
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                courseTitle = resultSet.getString("title");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseTitle;
    }
    public ResultSet readCourseCapacity(String courseTitle){
        try{
            // Connect to mysql db
            databaseConnection.connect();
            connection = databaseConnection.getConnection();
            //get capacity
            String sqlSelect = "SELECT capacity FROM course WHERE title = ?";
            PreparedStatement checkCapacity = connection.prepareStatement(sqlSelect);
            checkCapacity.setString(1, courseTitle);
            return checkCapacity.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void updateCourseCapacity(String courseTitle){
        try{
            ResultSet resultSet = readCourseCapacity(courseTitle);
            if(resultSet.next()) {
                int currentCapacity = resultSet.getInt("capacity");
                if(currentCapacity > 0) {
                    //Update capacity
                    String sqlUpdate = "UPDATE course SET capacity = ? WHERE title = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
                    if(CourseRegistrationController.registerFlag) {
                        updateStatement.setInt(1, currentCapacity - 1);
                        CourseRegistrationController.registerFlag = false;
                    }else if(CourseRegistrationController.dropFlag) {
                        updateStatement.setInt(1, currentCapacity + 1);
                        CourseRegistrationController.dropFlag = false;
                    }
                    updateStatement.setString(2, courseTitle);
                    int rowsAffected = updateStatement.executeUpdate();
                    if(rowsAffected > 0){
                        StudentView.displayCapacityUpdate();
                        updateStatement.close();
                    }else
                        StudentView.displayCapacityUpdateIssue();
                }else
                    StudentView.insufficientCapacityMsg();
            }else
                StudentView.displayNoCourseFound();
            resultSet.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
