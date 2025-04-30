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
public class LichTap {
    private LocalDateTime gioTap;
    private LocalDate thoiGianDen;
    private LocalDate thoiGianDi;
    private LocalDate ngayTap;
    
    
    public LocalDateTime getGioTap(){
        return gioTap;
    }
    public void setGioTap(LocalDateTime gioTap){
        this.gioTap = gioTap;   
    }
    public LocalDate getThoiGianDen(){
        return thoiGianDen;
    }
    public void setThoiGianDen(LocalDate thoiGianDen){
        this.thoiGianDen = thoiGianDen;
    }
    public LocalDate getThoiGianDi(){
        return thoiGianDi;
    }
    public void setThoiGianDi(LocalDate thoiGianDi){
        this.thoiGianDi = thoiGianDi;
    }
    public LocalDate getNgayTap(){
        return ngayTap;
    }
    public void setNgayTap(LocalDate ngayTap){
        this.ngayTap = ngayTap;
    }
}
