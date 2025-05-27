/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.HoiVienDAO;
import com.mycompany.project1.model.HoiVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Connection;
import com.mycompany.project1.utils.utils;
import javafx.scene.control.Alert;

/**
 *
 * @author dtquy
 */
public class HoiVienController {
    private ObservableList<HoiVien> danhSachHoiVien;
    private HoiVienDAO hoiVienDAO;
    
    public HoiVienController() {
        this.hoiVienDAO = new HoiVienDAO();
        this.danhSachHoiVien = FXCollections.observableArrayList();
        // Load dữ liệu từ database khi khởi tạo controller
        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
        danhSachHoiVien.clear();
        danhSachHoiVien.addAll(hoiVienDAO.getAllHoiVien());
    }
    
    // Thêm hội viên mới
    public boolean themHoiVien(int maHV, String hoTen, int namSinh, int sdt, 
                              String gioiTinh, String diaChi, String email, String canCuocCongDan) {
        try {
            // Tạo hội viên mới
            HoiVien hoiVienMoi = new HoiVien(0, hoTen, namSinh, sdt, gioiTinh, diaChi, email, canCuocCongDan);
            
            // Thêm vào database
            if (hoiVienDAO.themHoiVien(hoiVienMoi)) {
                // Refresh danh sách từ database
                loadDataFromDatabase();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm hội viên: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
            return false;
        }
    }
    
    // Tìm hội viên theo mã
    public HoiVien timHoiVienTheoMa(int maHV) {
        return hoiVienDAO.timHoiVienTheoMa(maHV);
    }
    
    // Tìm kiếm hội viên
    public ObservableList<HoiVien> timKiemHoiVien(String keyword) {
        ObservableList<HoiVien> ketQua = FXCollections.observableArrayList();
        for (HoiVien hv : danhSachHoiVien) {
            if (String.valueOf(hv.getMaHoiVien()).contains(keyword) ||
                hv.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                ketQua.add(hv);
            }
        }
        return ketQua;
    }
    
    // Xóa hội viên
    public boolean xoaHoiVien(int maHV) {
        if (hoiVienDAO.xoaHoiVien(maHV)) {
            danhSachHoiVien.removeIf(hv -> hv.getMaHoiVien() == maHV);
            return true;
        }
        return false;
    }
    
    // Cập nhật thông tin hội viên
    public boolean capNhatHoiVien(int maHV, String hoTen, int namSinh, int sdt, 
                                 String gioiTinh, String diaChi, String email, String canCuocCongDan) {
        HoiVien hv = timHoiVienTheoMa(maHV);
        if (hv != null) {
            hv.setHoTen(hoTen);
            hv.setSdt(sdt);
            hv.setGioiTinh(gioiTinh);
            hv.setDiaChi(diaChi);
            hv.setEmail(email);
            hv.setCanCuocCongDan(canCuocCongDan);
            
            if (hoiVienDAO.capNhatHoiVien(hv)) {
                // Refresh data from database
                loadDataFromDatabase();
                return true;
            }
        }
        return false;
    }
    
    // Lấy danh sách hội viên
    public ObservableList<HoiVien> getDanhSachHoiVien() {
        return danhSachHoiVien;
    }
    
    // Validate dữ liệu nhập vào
    public boolean validateInput(String maHV, String hoTen, String namSinh, 
                               String sdt, String gioiTinh, String diaChi, String email, String canCuocCongDan) {
        try {
            // Kiểm tra họ tên
            if (hoTen == null || hoTen.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Họ tên không được để trống!", Alert.AlertType.ERROR);
                System.out.println("Họ tên không được để trống!");
                return false;
            }
            
            // Kiểm tra năm sinh
            if (namSinh == null || namSinh.trim().isEmpty()) {
                System.out.println("Năm sinh không được để trống!");
                return false;
            }
            try {
                int year = Integer.parseInt(namSinh);
                int currentYear = java.time.Year.now().getValue();
                if (year < 1900 || year > currentYear) {
                    utils.showAlert("Lỗi", "Năm sinh không hợp lệ!", Alert.AlertType.ERROR);
                    //System.out.println("Năm sinh không hợp lệ!");
                    return false;
                }
            } catch (NumberFormatException e) {
                utils.showAlert("Lỗi", "Năm sinh phải là số!", Alert.AlertType.ERROR);
                //System.out.println("Năm sinh phải là số!");
                return false;
            }
            
            // Kiểm tra số điện thoại
            if (sdt == null || sdt.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Số điện thoại không được để trống!", Alert.AlertType.ERROR);
                //System.out.println("Số điện thoại không được để trống!"); 
                return false;
            }
            try {
                long phone = Long.parseLong(sdt);
                if (phone < 0) {
                    utils.showAlert("Lỗi", "Số điện thoại không hợp lệ!", Alert.AlertType.ERROR);
                    //System.out.println("Số điện thoại không hợp lệ!");
                    return false;
                }
            } catch (NumberFormatException e) {
                utils.showAlert("Lỗi", "Số điện thoại phải là số!", Alert.AlertType.ERROR);
                //System.out.println("Số điện thoại phải là số!");
                return false;
            }
            
            // Kiểm tra giới tính
            if (gioiTinh == null || gioiTinh.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Giới tính không được để trống!", Alert.AlertType.ERROR);
                //System.out.println("Giới tính không được để trống!");
                return false;
            }
            
            // Kiểm tra địa chỉ
            if (diaChi == null || diaChi.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Địa chỉ không được để trống!", Alert.AlertType.ERROR);
                //System.out.println("Địa chỉ không được để trống!");
                return false;
            }
            
            // Kiểm tra email
            if (email == null || email.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Email không được để trống!", Alert.AlertType.ERROR);
                //System.out.println("Email không được để trống!");
                return false;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                utils.showAlert("Lỗi", "Email không đúng định dạng!", Alert.AlertType.ERROR);
                //System.out.println("Email không đúng định dạng!");
                return false;
            }

            // Kiểm tra căn cước công dân
            if (canCuocCongDan == null || canCuocCongDan.trim().isEmpty()) {
                utils.showAlert("Lỗi", "Căn cước công dân không được để trống!", Alert.AlertType.ERROR);
                //System.out.println("Căn cước công dân không được để trống!");
                return false;
            }
            if (!canCuocCongDan.matches("\\d{9,12}")) {
                utils.showAlert("Lỗi", "Căn cước công dân phải có 9-12 chữ số!", Alert.AlertType.ERROR);
                //System.out.println("Căn cước công dân phải có 9-12 chữ số!");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi validate dữ liệu: " + e.getMessage());
            return false;
        }
    }

    // Check-in hội viên
    public boolean checkInHoiVien(int maHV) {
        HoiVien hv = timHoiVienTheoMa(maHV);
        if (hv != null) {
            // Kiểm tra xem hội viên đã check-in hôm nay chưa
            if (!hoiVienDAO.kiemTraCheckIn(maHV, LocalDate.now())) {
                // Thực hiện check-in
                return hoiVienDAO.checkInHoiVien(maHV, LocalDate.now(), LocalTime.now());
            }
        }
        return false;
    }

    // Lấy danh sách hội viên đã check-in trong ngày
    public ObservableList<HoiVien> getDanhSachHoiVienCheckIn(LocalDate date) {
        return hoiVienDAO.getDanhSachHoiVienCheckIn(date);
    }

    // Lấy thống kê số lần tập của hội viên
    public int getSoLanTap(int maHV, LocalDate startDate, LocalDate endDate) {
        return hoiVienDAO.getSoLanTap(maHV, startDate, endDate);
    }

    // Lấy thống kê số hội viên tập theo ngày
    public int getSoHoiVienTapTheoNgay(LocalDate date) {
        return hoiVienDAO.getSoHoiVienTapTheoNgay(date);
    }

    // Lấy thống kê số hội viên tập theo tuần
    public int getSoHoiVienTapTheoTuan(LocalDate startDate) {
        return hoiVienDAO.getSoHoiVienTapTheoTuan(startDate);
    }

    // Lấy thống kê số hội viên tập theo tháng
    public int getSoHoiVienTapTheoThang(LocalDate startDate) {
        return hoiVienDAO.getSoHoiVienTapTheoThang(startDate);
    }

    // Lấy lịch sử tập luyện của hội viên
    public ObservableList<HoiVien> getLichSuTapLuyen(int maHV, LocalDate startDate, LocalDate endDate) {
        return hoiVienDAO.getLichSuTapLuyen(maHV, startDate, endDate);
    }

    // Lấy lịch sử tập luyện của tất cả hội viên
    public ObservableList<HoiVien> getLichSuTapLuyen(LocalDate startDate, LocalDate endDate) {
        return hoiVienDAO.getLichSuTapLuyen(startDate, endDate);
    }

    public boolean checkOutHoiVien(int maHoiVien) {
        return hoiVienDAO.checkOutHoiVien(maHoiVien);
    }

    public int getTongSoHoiVien() {
        return hoiVienDAO.getTongSoHoiVien();
    }
}
