/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;

/**
 *
 * @author dtquy
 */
public class User {
    private int UserID;
    private String HoTen; 
    private String Email;
    private String Password;
    private String Role;
    private int UserYoB;
    private String Gender;
    private String Address;
    private int PhoneNum;
    private int CitizenIdenNumb;
    
    public User(int userID, String HoTen, String Email, String Password, String Role,int UserYoB, String Gender, String Address, int PhoneNum, int CitizenIdenNumb ){
        this.UserID = userID;
        this.HoTen = HoTen;
        this.Email = Email;
        this.Role = Role;
        this.Password = Password;
        this.Gender = Gender;
        this.UserYoB = UserYoB;
        this.Address = Address;
        this.PhoneNum = PhoneNum;
        this.CitizenIdenNumb =  CitizenIdenNumb;
    }
    
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    // Getter và Setter cho HoTen
    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen = hoTen;
    }

    // Getter và Setter cho Email
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    // Getter và Setter cho Password
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    // Getter và Setter cho Role
    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }
}


