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
    private String UserName; 
    private String Email;
    private String Password;
    private String Role;
    private int UserYoB;
    private String Gender;
    private String Address;
    private String PhoneNum;
    private String CitizenIdenNumb;
    
    public User() {
    }
    
    public User(int userID, String username, String password, String role, String fullName, String email) {
        this.UserID = userID;
        this.UserName = username;
        this.Password = password;
        this.Role = role;
        this.Email = email;
    }
    
    public User(int userID, String UserName, String Email, String Password, String Role,int UserYoB, String Gender, String Address, String PhoneNum, String CitizenIdenNumb ){
        this.UserID = userID;
        this.UserName = UserName;
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
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    // Getter và Setter cho Email
  

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

    public int getUserYoB() {
        return UserYoB;
    }

    public void setUserYoB(int userYoB) {
        this.UserYoB = userYoB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.PhoneNum = phoneNum;
    }

  
    

    public String getUserEmail() {
        return Email;
    }

    public void setUserEmail(String email) {
        this.Email = email;
    }

    public String getCitizenIdentification() {
        return CitizenIdenNumb;
    }

    public void setCitizenIdentification(String citizenIdenNumb) {
        this.CitizenIdenNumb = citizenIdenNumb;
    }
}


