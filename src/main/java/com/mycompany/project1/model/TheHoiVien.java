/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author dtquy
 */
public abstract class TheHoiVien {
    
    protected int maThe;
    protected int maHoiVien;
    protected String loaiThe;
    protected int classID;
    protected String SubId;
    
    public int getMathe(){
        return maThe;
    }
    public void setMaThe(int maThe){
        this.maThe = maThe;
    }
    public String getLoaiThe(){
        return loaiThe;
    }
    public void setLoaiThe(String loaiThe){
        this.loaiThe=loaiThe;
    }
    
    public abstract void QuyenLoi();
  
}

