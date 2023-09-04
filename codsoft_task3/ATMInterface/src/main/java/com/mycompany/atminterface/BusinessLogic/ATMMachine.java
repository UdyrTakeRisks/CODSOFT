/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterface.BusinessLogic;

import com.mycompany.atminterface.DataAccess.FileService;
import com.mycompany.atminterface.DataAccess.UserAccount;
import java.io.IOException;

/**
 *
 * @author ahmed
 */
public class ATMMachine {
    
    private final UserAccount userAccount; 
    private final FileService file;
    
    public ATMMachine(){
        userAccount = new UserAccount(); 
        file = new FileService();
    }
    
    public UserAccount getUserAccount(){
        return userAccount;
    }
    
    public FileService getFileService(){
        return file;
    }
    
    public boolean checkInvalidWithdraw(double amount){
        double dailyLimit = 10000.0;
        // returns true if withdraw is invalid, false if withdraw is valid
        return (amount <= 0 || amount > userAccount.getBalance() || amount > dailyLimit);
    }
    
    public void withdraw(double amount) throws IOException{
        userAccount.setBalance(userAccount.getBalance() - amount);
        file.writeBalanceToFile(userAccount.getName(),userAccount.getPIN(),userAccount.getBalance());
    }
    
    public boolean checkInvalidDeposit(double amount){
        // returns true if deposit is invalid, false if deposit is valid
        return (amount <= 0);
    }
    
    public void deposit(double amount) throws IOException{
        userAccount.setBalance(userAccount.getBalance() + amount);
        file.writeBalanceToFile(userAccount.getName(),userAccount.getPIN(),userAccount.getBalance());
    }
    
    public double checkBalance(){
        return userAccount.getBalance();
    }
    
    public void logUser(String name, int PIN){
        userAccount.setName(name);
        userAccount.setPIN(PIN);  
    }
    
    public boolean checkValidPin(int PIN){
        // exceptions
        if(PIN < 0){
            return false;
        }
        return (PIN <= 9999); //true, don't exceed the 5 numbers
    }
    
    public void checkUserAccountExistence(String name, int PIN) throws IOException{
        
        file.setUserNameToFile(name);
        // if the user exists
        if(file.checkUserNameFromFile(name) && file.checkUserPINFromFile(PIN)){
            logUser(name,PIN);
            if(userAccount.getBalance() != 0) //set the balance of the old user
                userAccount.setBalance(file.readBalanceFromFile(name, PIN)); 
        }
        if(file.checkUserNameFromFile(name) && file.readPINFromFile(name, PIN) != PIN){
            return;
        }
        else{ // if the user is new
            if(file.checkUserNameFromFile(name) == false){
                logUser(name,PIN);
                file.writeToFile(name,PIN);
                userAccount.setBalance(0.0);
            }
        }
    }
}
