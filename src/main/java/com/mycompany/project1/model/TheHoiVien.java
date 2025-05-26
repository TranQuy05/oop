/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author dtquy
 */
public class TheHoiVien {
    private int cardID;
    private String type;
    private double price;
    private int validDuration;
    private LocalDate purchaseDate;
    private List<GoiDangKy> danhSachGoi;

    public TheHoiVien() {
    }

    public TheHoiVien(int cardID, String type, double price, int validDuration) {
        this.cardID = cardID;
        this.type = type;
        this.price = price;
        this.validDuration = validDuration;
    }

    // Getters and Setters
    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getValidDuration() {
        return validDuration;
    }

    public void setValidDuration(int validDuration) {
        this.validDuration = validDuration;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<GoiDangKy> getDanhSachGoi() {
        return danhSachGoi;
    }

    public void setDanhSachGoi(List<GoiDangKy> danhSachGoi) {
        this.danhSachGoi = danhSachGoi;
    }

    // Thêm gói đăng ký mới
    public void themGoiDangKy(GoiDangKy goi) {
        danhSachGoi.add(goi);
    }
}

