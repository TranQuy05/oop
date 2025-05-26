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
public class NhanVien {
    private int staffID;
    private String startWorkingDate;
    private User user;

    public NhanVien() {
    }

    public NhanVien(int staffID, String startWorkingDate, User user) {
        this.staffID = staffID;
        this.startWorkingDate = startWorkingDate;
        this.user = user;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(String startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

//     public NhanVien(int maNhanVien,String hoTen,String chucVu,int ngaySinh,String gioiTinh,
//             String soDienThoai,String diaChi,LocalDate ngayVaoLam){
//         this.maNhanVien=maNhanVien;
//         this.hoTen = hoTen;
//         this.chucVu=chucVu;
//         this.namSinh = namSinh;
//         this.gioiTinh=gioiTinh;
//         this.soDienThoai=soDienThoai;
//         this.diaChi=diaChi;
//         this.ngayVaoLam= ngayVaoLam;
//     }
//     public int getMaNhanVien() {
//        return maNhanVien;
//    }
//
//    public void setMaNhanVien(int maNhanVien) {
//        this.maNhanVien = maNhanVien;
//    }
//    
//    public String getHoTen(){
//        return hoTen;
//    }
//    public void setHoTen(String hoTen){
//        this.hoTen = hoTen;
//    }
//    
//    public String getChucVu() {
//        return chucVu;
//    }
//
//    public void setChucVu(String chucVu) {
//        this.chucVu = chucVu;
//    }
//
//    public int getNamSinh() {
//        return namSinh;
//    }
//
//    public void setNamSinh(int namSinh) {
//        this.namSinh = namSinh;
//    }
//
//    public String getGioiTinh() {
//        return gioiTinh;
//    }
//
//    public void setGioiTinh(String gioiTinh) {
//        this.gioiTinh = gioiTinh;
//    }
//
//    public String getSoDienThoai() {
//        return soDienThoai;
//    }
//
//    public void setSoDienThoai(String soDienThoai) {
//        this.soDienThoai = soDienThoai;
//    }
//
//    public String getDiaChi() {
//        return diaChi;
//    }
//
//    public void setDiaChi(String diaChi) {
//        this.diaChi = diaChi;
//    }
//
//    public LocalDate getNgayVaoLam() {
//        return ngayVaoLam;
//    }
//
//    public void setNgayVaoLam(LocalDate ngayVaoLam) {
//        this.ngayVaoLam = ngayVaoLam;
//    }
//
//     public void hienThiThongTin() {
//        System.out.println("===== THÔNG TIN NHÂN VIÊN =====");
//        System.out.println("Mã nhân viên: " + maNhanVien);
//        System.out.println("Chức vụ: " + chucVu);
//        System.out.println("Ngày sinh: " + namSinh);
//        System.out.println("Giới tính: " + gioiTinh);
//        System.out.println("Số điện thoại: " + soDienThoai);
//        System.out.println("Địa chỉ: " + diaChi);
//        System.out.println("Ngày vào làm: " + ngayVaoLam);
//   
//        System.out.println("================================");
//    }
//}
//
