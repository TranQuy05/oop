package com.mycompany.project1.model;

import java.time.LocalDate;

public class TheHoiVien_HoiVien {
    private int TheHoiVien_HoiVienID;
    private int maHoiVien;
    private int maThe;
    private LocalDate HanThanhToan;

    public TheHoiVien_HoiVien(int TheHoiVien_HoiVienID, int maHoiVien, int maThe, LocalDate HanThanhToan) {
        this.TheHoiVien_HoiVienID = TheHoiVien_HoiVienID;
        this.maHoiVien = maHoiVien;
        this.maThe = maThe;
        this.HanThanhToan = HanThanhToan;
    }

 
}