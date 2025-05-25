/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;
import java.time.LocalDateTime;

/**
 *
 * @author dtquy
 */
public class HLV extends User{
    private String lopquanli;
    private String ChuyenMon; // chuyên môn hlv
    private String MoTa; // mô tả kinh nghiệm

    public HLV(int userID, String HoTen, String Email, String Password, String Role, int UserYoB, String Gender, String Address, int PhoneNum, int CitizenIdenNumb) {
        super(userID, HoTen, Email, Password, Role, UserYoB, Gender, Address, PhoneNum, CitizenIdenNumb);
    }

   
    

    
    public void setLopquanli(String lopquanli){
        this.lopquanli = lopquanli;
    }
    public String getLopquanli(){
        return lopquanli;
        }

    public void setChuyenMon(String chuyenMon){
        this.ChuyenMon = chuyenMon;
    }
    public String getChuyenMon(){
        return ChuyenMon;
    }
    
    public void setMoTa(String moTa){
        this.MoTa = moTa;
    }
    public String getMoTa(){
        return MoTa;
    }
 
    public LichTap TaoLichTap(int maLop, LocalDateTime ThoiGianBatDau, LocalDateTime ThoiGianKetThuc, String MoTa) {
        // Tạo mới lịch học
        LichTap lichTap = new LichTap(maLop, this.getUserID(), ThoiGianBatDau, ThoiGianKetThuc, MoTa);
        // Có thể lưu vào database hoặc danh sách nếu cần
        return lichTap;
    }
}
