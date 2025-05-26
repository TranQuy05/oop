package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.GoiDangKyDAO;
import com.mycompany.project1.model.GoiDangKy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoiDangKyController {
    private ObservableList<GoiDangKy> danhSachGoiDangKy;
    private GoiDangKyDAO goiDangKyDAO;
    
    public GoiDangKyController() {
        this.goiDangKyDAO = new GoiDangKyDAO();
        this.danhSachGoiDangKy = FXCollections.observableArrayList();
        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
        danhSachGoiDangKy.clear();
        danhSachGoiDangKy.addAll(goiDangKyDAO.getAllGoiDangKy());
    }
    
    public boolean themGoiDangKy(GoiDangKy goi) {
        try {
            if (goiDangKyDAO.themGoiDangKy(goi)) {
                danhSachGoiDangKy.add(goi);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm gói đăng ký: " + e.getMessage());
            return false;
        }
    }
    
    public GoiDangKy timGoiDangKyTheoMa(int maGoi) {
        return goiDangKyDAO.timGoiDangKyTheoMa(maGoi);
    }
    
    public ObservableList<GoiDangKy> timKiemGoiDangKy(String keyword) {
        ObservableList<GoiDangKy> ketQua = FXCollections.observableArrayList();
        for (GoiDangKy goi : danhSachGoiDangKy) {
            if (String.valueOf(goi.getSubscriptionID()).contains(keyword) ||
                goi.getSubName().toLowerCase().contains(keyword.toLowerCase())) {
                ketQua.add(goi);
            }
        }
        return ketQua;
    }
    
    public boolean xoaGoiDangKy(int maGoi) {
        if (goiDangKyDAO.xoaGoiDangKy(maGoi)) {
            danhSachGoiDangKy.removeIf(goi -> goi.getSubscriptionID() == maGoi);
            return true;
        }
        return false;
    }
    
    public boolean capNhatGoiDangKy(GoiDangKy goi) {
        if (goiDangKyDAO.capNhatGoiDangKy(goi)) {
            loadDataFromDatabase();
            return true;
        }
        return false;
    }
    
    public ObservableList<GoiDangKy> getDanhSachGoiDangKy() {
        return danhSachGoiDangKy;
    }
} 