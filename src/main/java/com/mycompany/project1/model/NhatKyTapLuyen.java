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
public class NhatKyTapLuyen {
    private int logID;
    private int maHoiVien;
    private int MaHlv;
    private LocalDate LichTap;
    
     public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getMaHoiVien() {
        return maHoiVien;
    }

    public void setMaHoiVien(int maHoiVien) {
        this.maHoiVien = maHoiVien;
    }

    public int getMaHlv() {
        return MaHlv;
    }

    public void setMaHlv(int MaHlv) {
        this.MaHlv = MaHlv;
    }

    public LocalDate getLichTap() {
        return LichTap;
    }

    public void setLichTap(LocalDate lichTap) {
        this.LichTap = lichTap;
    }
     public NhatKyTapLuyen(int logID, int maHoiVien, int maHlv, LocalDate lichTap) {
        this.logID = logID;
        this.maHoiVien = maHoiVien;
        this.MaHlv = maHlv;
        this.LichTap = lichTap;
    }

    // Phương thức thêm nhật ký tập luyện
    public void addLog(int logID, int maHoiVien, int maHlv, LocalDate lichTap) {
        this.logID = logID;
        this.maHoiVien = maHoiVien;
        this.MaHlv = maHlv;
        this.LichTap = lichTap;
    }

    // Phương thức cập nhật nhật ký tập luyện
    public void updateLog(int maHoiVien, int maHlv, LocalDate lichTap) {
        this.maHoiVien = maHoiVien;
        this.MaHlv = maHlv;
        this.LichTap = lichTap;
    }
}
