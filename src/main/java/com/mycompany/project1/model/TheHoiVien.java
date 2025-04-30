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
public class TheHoiVien {
    
    private String maThe;
    private String maHoiVien;
    private String loaiThe;
    private LocalDate ngayKichHoat;
    private LocalDate ngayHetHan;
    public String getMathe(){
        return maThe;
    }
    public void setMaThe(String maThe){
        this.maThe = maThe;
    }
    public String getLoaiThe(){
        return maThe;
    }
    public void setLoaiThe(String loaiThe){
        this.loaiThe=loaiThe;
    }
    public LocalDate getNgayKichHoat(){
        return ngayKichHoat;
    }
    public void setNgayKichHoat(LocalDate ngayKichHoat){
        this.ngayKichHoat = ngayKichHoat;
    }
    public LocalDate getNgayHetHan(){
        return ngayHetHan;
    }
    public void setNgayHetHan(LocalDate ngayHetHan){
        this.ngayHetHan=ngayHetHan;
    
}

}
