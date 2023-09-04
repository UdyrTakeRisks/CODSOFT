/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterface.DataAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class FileService {
    
    private File fileName;

    public void setUserNameToFile(String name) {
        this.fileName = new File(name + ".txt");
    }

    public File getFileName(){
        return fileName;
    }
    
    public void writeToFile(String name, int PIN){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
            writer.write("Name: "+ name + "\n");
            writer.write("PIN: " + PIN  + "\n");
            writer.close();
        }catch(IOException e){
            
        }
    }
    public void writeBalanceToFile(String name, int PIN, double balance) throws IOException{
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
        while ((line = reader.readLine()) != null) {
            if(checkUserNameFromFile(name) == true && checkUserPINFromFile(PIN) == true){
                if(line.contains("PIN: ")){
                    writer.write("Balance: " + balance + "\n");
                    writer.write("Date: " + showDate()); 
                    writer.newLine();
                }
            }
        }
        writer.close();
    }
    public double readBalanceFromFile(String name, int PIN) throws FileNotFoundException, IOException{
        String balanceString = " ";
        double balance = 0.0;
        if(fileName.exists()){
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine()) != null){
                
                if(checkUserNameFromFile(name) == true && checkUserPINFromFile(PIN) == true){
                    
                    if(line.startsWith("Balance: ")){  
                        
                        String[] parts = line.split(":");
                        
                        if(parts.length >= 2){
                            balanceString = parts[1].trim();
                        }
                    }
                }
            }    
        }
        if(balanceString != null)
            balance = Double.parseDouble(balanceString); 
        
        return balance;
    } 
    public ArrayList<String> readFromFile(String name) throws FileNotFoundException, IOException{
        File userLog = new File(name + ".txt");
        ArrayList<String> contentList = new ArrayList<>();
        if(userLog.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(userLog)); 
            String content;
            while((content = reader.readLine()) != null){
                contentList.add("\n" + content + "\n");
            } 
        }
        return contentList; 
    }
    
    public boolean checkUserNameFromFile(String name){
        try{
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                if(line.startsWith("Name: ") && line.contains(name))
                    return true;
            }
        }catch(IOException e){
            
        }
        return false;
    }
    
    public boolean checkUserPINFromFile(int PIN){ //not working function , needs fix
        try{
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                if(line.contains("PIN: " + PIN))
                    return true;
            }
        }catch(IOException e){
            
        }
        return false;
    }
    
    public int readPINFromFile(String name, int PIN) throws FileNotFoundException, IOException {
        int pin = 0;
        if (fileName.exists()) {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.contains(name)) {
                    String pinLine = reader.readLine(); // Read the next line after finding the name
                    String[] parts = pinLine.split(":");
                    if (parts.length >= 2) {
                        String pinString = parts[1].trim();
                        try {
                            pin = Integer.parseInt(pinString);
                        } catch (NumberFormatException e) {
                            // Handle the exception if the pinString cannot be parsed as an integer

                        }
                        break; // Exit the loop after finding the PIN
                    }
                }
            }
            reader.close(); // Close the reader after reading
        }
        return pin;
    }
    
    public String showDate(){
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        //Date start = Date.from(Instant.now()); //usual system time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1); //summer system time
        Date start = calendar.getTime();
        return Format.format(start);
    }
}
