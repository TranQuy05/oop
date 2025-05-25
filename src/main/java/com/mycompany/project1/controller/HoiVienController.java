/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.controller;

import com.mycompany.project1.model.HoiVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author dtquy
 */
public class HoiVienController {
    private ObservableList<HoiVien> danhSachHoiVien;
    
    public HoiVienController() {
        this.danhSachHoiVien = FXCollections.observableArrayList();
    }
    
    // Thêm hội viên mới
    public boolean themHoiVien(int maHV, String hoTen, int namSinh, int sdt, 
                              String gioiTinh, String diaChi, String email) {
        try {
            // Kiểm tra mã hội viên đã tồn tại chưa
            if (timHoiVienTheoMa(maHV) != null) {
                System.out.println("Mã hội viên đã tồn tại!");
                return false;
            }
            
            // Tạo hội viên mới
            HoiVien hoiVienMoi = new HoiVien(maHV, hoTen, namSinh, sdt, gioiTinh, diaChi, email);
            danhSachHoiVien.add(hoiVienMoi);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm hội viên: " + e.getMessage());
            return false;
        }
    }
    
    // Tìm hội viên theo mã
    public HoiVien timHoiVienTheoMa(int maHV) {
        for (HoiVien hv : danhSachHoiVien) {
            if (hv.getmaHoiVien() == maHV) {
                return hv;
            }
        }
        return null;
    }
    
    // Tìm kiếm hội viên
    public ObservableList<HoiVien> timKiemHoiVien(String keyword) {
        ObservableList<HoiVien> ketQua = FXCollections.observableArrayList();
        for (HoiVien hv : danhSachHoiVien) {
            if (String.valueOf(hv.getmaHoiVien()).contains(keyword) ||
                hv.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                ketQua.add(hv);
            }
        }
        return ketQua;
    }
    
    // Xóa hội viên
    public boolean xoaHoiVien(int maHV) {
        HoiVien hv = timHoiVienTheoMa(maHV);
        if (hv != null) {
            danhSachHoiVien.remove(hv);
            return true;
        }
        return false;
    }
    
    // Cập nhật thông tin hội viên
    public boolean capNhatHoiVien(int maHV, String hoTen, int namSinh, int sdt, 
                                 String gioiTinh, String diaChi, String email) {
        HoiVien hv = timHoiVienTheoMa(maHV);
        if (hv != null) {
            hv.setHoTen(hoTen);
            hv.setSdt(sdt);
            hv.setGioiTinh(gioiTinh);
            hv.setDiaChi(diaChi);
            hv.setEmail(email);
            return true;
        }
        return false;
    }
    
    // Lấy danh sách hội viên
    public ObservableList<HoiVien> getDanhSachHoiVien() {
        return danhSachHoiVien;
    }
    
    // Validate dữ liệu nhập vào
    public boolean validateInput(String maHV, String hoTen, String namSinh, 
                               String sdt, String gioiTinh, String diaChi, String email) {
        try {
            // Kiểm tra mã hội viên
            if (maHV.isEmpty() || !maHV.matches("\\d+")) {
                System.out.println("Mã hội viên không hợp lệ!");
                return false;
            }
            
            // Kiểm tra họ tên
            if (hoTen.isEmpty()) {
                System.out.println("Họ tên không được để trống!");
                return false;
            }
            
            // Kiểm tra năm sinh
            if (namSinh.isEmpty() || !namSinh.matches("\\d{4}")) {
                System.out.println("Năm sinh không hợp lệ!");
                return false;
            }
            
            // Kiểm tra số điện thoại
            if (sdt.isEmpty() || !sdt.matches("\\d{10}")) {
                System.out.println("Số điện thoại không hợp lệ!");
                return false;
            }
            
            // Kiểm tra giới tính
            if (gioiTinh.isEmpty()) {
                System.out.println("Giới tính không được để trống!");
                return false;
            }
            
            // Kiểm tra địa chỉ
            if (diaChi.isEmpty()) {
                System.out.println("Địa chỉ không được để trống!");
                return false;
            }
            
            // Kiểm tra email
            if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Email không hợp lệ!");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi validate dữ liệu: " + e.getMessage());
            return false;
        }
    }
}
