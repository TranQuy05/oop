/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;

import java.time.LocalDate;

/**
 *
 * @author dtquy
 */
public class GoiDangKy {
    private int SubsID;
    private String Type;
    private String Price;
    private LocalDate duration;
    
    public GoiDangKy( int SubsID, String Type, String Price, LocalDate duration ){
        this.SubsID = SubsID;
        this.Type = Type;
        this.Price = Price;
        this.duration = duration;
    }
    public int getSubsID() {
        return SubsID;
    }

    public void setSubsID(int subsID) {
        this.SubsID = subsID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }
   
}
