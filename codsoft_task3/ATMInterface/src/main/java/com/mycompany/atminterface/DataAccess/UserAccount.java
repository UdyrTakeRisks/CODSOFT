/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterface.DataAccess;

/**
 *
 * @author ahmed
 */
public class UserAccount {
    
    private String name = "N/A"; 
    
    private int PIN = 0;
    
    private double balance = 0.0;
    
    public UserAccount(){}
    
    public UserAccount(String name, int PIN, double balance){
        this.name = name;
        this.PIN = PIN;
        this.balance = balance;
    }
    
    // setters and getters
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPIN(int PIN){
        this.PIN = PIN;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPIN(){
        return PIN;
    }
    
    public double getBalance(){
        return balance;
    }
    
}
