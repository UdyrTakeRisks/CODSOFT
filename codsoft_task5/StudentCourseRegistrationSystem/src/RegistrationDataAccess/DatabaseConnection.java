package RegistrationDataAccess;

import java.sql.*;
public class DatabaseConnection {
    private Connection connection;
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DatabaseConnection(String jdbcUrl, String username, String password){
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database Connected!"); //debug
        }catch(SQLException e){
            System.err.println("Database Connection Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        if(connection != null){
            try{
                connection.close();
                System.out.println("Database Disconnected!"); //debug
            } catch (SQLException e) {
                System.err.println("Database Disconnection Error: " + e.getMessage());
            }
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
