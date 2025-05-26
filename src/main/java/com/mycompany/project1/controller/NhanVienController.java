/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.NhanVienDAO;
import com.mycompany.project1.model.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dtquy
 */
public class NhanVienController {
    private ObservableList<NhanVien> danhSachNhanVien;
    private NhanVienDAO nhanVienDAO;
    
    public NhanVienController() {
        this.nhanVienDAO = new NhanVienDAO();
        this.danhSachNhanVien = FXCollections.observableArrayList();
        // Load dữ liệu từ database khi khởi tạo controller
        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
        danhSachNhanVien.clear();
        danhSachNhanVien.addAll(nhanVienDAO.getAllNhanVien());
    }
    
    // Thêm nhân viên mới
    public boolean themNhanVien(NhanVien nhanVien) {
        try {
            // Kiểm tra mã nhân viên đã tồn tại chưa
            if (timNhanVienTheoMa(nhanVien.getStaffID()) != null) {
                System.out.println("Mã nhân viên đã tồn tại!");
                return false;
            }
            // Thêm vào database
            if (nhanVienDAO.themNhanVien(nhanVien)) {
                danhSachNhanVien.add(nhanVien);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            return false;
        }
    }
    
    // Tìm nhân viên theo mã
    public NhanVien timNhanVienTheoMa(int maNV) {
        return nhanVienDAO.timNhanVienTheoMa(maNV);
    }
    
    // Tìm kiếm nhân viên
    public ObservableList<NhanVien> timKiemNhanVien(String keyword) {
        ObservableList<NhanVien> ketQua = FXCollections.observableArrayList();
        for (NhanVien nv : danhSachNhanVien) {
            if (String.valueOf(nv.getStaffID()).contains(keyword) ||
                nv.getUser().getUserName().toLowerCase().contains(keyword.toLowerCase())) {
                ketQua.add(nv);
            }
        }
        return ketQua;
    }
    
    // Xóa nhân viên
    public boolean xoaNhanVien(int maNV) {
        if (nhanVienDAO.xoaNhanVien(maNV)) {
            danhSachNhanVien.removeIf(nv -> nv.getStaffID() == maNV);
            return true;
        }
        return false;
    }
    
    // Cập nhật thông tin nhân viên
    public boolean capNhatNhanVien(NhanVien nhanVien) {
        if (nhanVienDAO.capNhatNhanVien(nhanVien)) {
            // Refresh data from database
            loadDataFromDatabase();
            return true;
        }
        return false; 
    }
    
    // Lấy danh sách nhân viên
    public ObservableList<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }
}
