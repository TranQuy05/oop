package com.mycompany.project1.controller;

import com.mycompany.project1.model.LopHoc;
import com.mycompany.project1.DAO.LopHocDAO;
import javafx.collections.ObservableList;

public class LopHocController {
    private LopHocDAO dao;

    public LopHocController() {
        this.dao = new LopHocDAO();
    }

    public ObservableList<LopHoc> getDanhSachLopHoc() {
        return dao.getDanhSachLopHoc();
    }

    public ObservableList<String> getDanhSachHuongDanVien() {
        return dao.getDanhSachHuongDanVien();
    }

    public int getTrainerIDFromName(String trainerName) {
        return dao.getTrainerIDFromName(trainerName);
    }

    public boolean themLopHoc(String tenLop, int trainerID, String lichHoc) {
        return dao.themLopHoc(tenLop, trainerID, lichHoc);
    }

    public boolean xoaLopHoc(int classID) {
        return dao.xoaLopHoc(classID);
    }

    public boolean capNhatLopHoc(LopHoc lop) {
        return dao.capNhatLopHoc(lop);
    }
} 