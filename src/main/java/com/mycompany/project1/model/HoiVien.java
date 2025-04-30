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
public class HoiVien {
    private int maHoiVien; // Mã thẻ hội viên
    private String hoTen; // Họ tên hội viên
    private int namSinh;
    private int sdt; // Số điện thoại hội viên
    private String gioiTinh;
    private String diaChi; // Đia chỉ
    private String email;//email cua hoi vien
    
    public int getmaHoiVien(){
    return maHoiVien;}
    public void setmaHoiVien(int maHoiVien){
        this.maHoiVien=maHoiVien;
    }
    
     public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
        
    }
    public int getNamSinh(){
        return namSinh;
    }
    public int getSdt() {
        return sdt;
    }
    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
    public String getGioiTinh(){
        return gioiTinh;
        }
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh=gioiTinh;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
        
    public HoiVien(int maHoiVien, String hoTen, int namSinh, int sdt, String gioiTinh, String diaChi, String email){
        this.maHoiVien = maHoiVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.email = email;
           }
    
    public void hienThiTHongTin(){
        System.out.println("Ma the : " + maHoiVien);
        System.out.println("Ho va ten :" + hoTen);
        
        System.out.println("So dien thoai :" + sdt);
        System.out.println("Dia chi :" + diaChi);
        
    }
}
