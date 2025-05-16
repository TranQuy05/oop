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
public class HLV extends NhanVien{
    private String lopquanli;
    private int    sohoivienquanly;
    
    
    public HLV(int maNhanVien, String hoTen, String chucVu, int ngaySinh, String gioiTinh, String soDienThoai, String diaChi, LocalDate ngayVaoLam) {
        super(maNhanVien, hoTen, chucVu, ngaySinh, gioiTinh, soDienThoai, diaChi, ngayVaoLam);
    }
    
}
