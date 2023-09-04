package RegistrationDataAccess.Models;

public class Student {
    private String name;
    private int id;

    //setters and getters

    public void setName(String name){
        this.name = name;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }
}
