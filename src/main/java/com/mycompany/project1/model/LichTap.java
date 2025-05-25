package com.mycompany.project1.model;

import java.time.LocalDateTime;

public class LichTap {
    private int lichTapID;
    private int maLop;         // Mã lớp học
    private int maHLV;       // Mã HLV
    private LocalDateTime ThoiGianBatDau;
    private LocalDateTime ThoiGianKetThuc;
    private String MoTa;  // Mô tả nội dung buổi học

    public LichTap(int lichTapID, int maLop, int maHLV, LocalDateTime ThoiGianBatDau, LocalDateTime ThoiGianKetThuc, String MoTa) {
        this.lichTapID = lichTapID;
        this.maLop = maLop;
        this.maHLV = maHLV;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.MoTa = MoTa;
    }

    // Constructor không có lichTapID (dùng khi chưa biết ID)
    public LichTap(int maLop, int maHLV, LocalDateTime ThoiGianBatDau, LocalDateTime ThoiGianKetThuc, String MoTa) {
        this.maLop = maLop;
        this.maHLV = maHLV;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.MoTa = MoTa;
    }

    // Getter & Setter
    public int getLichTapID() { return lichTapID; }
    public void setLichTapID(int lichTapID) { this.lichTapID = lichTapID; }

    public int getMaLop() { return maLop; }
    public void setMaLop(int maLop) { this.maLop = maLop; }

    public int getMaHLV() { return maHLV; }
    public void setMaHLV(int maHLV) { this.maHLV = maHLV; }

    public LocalDateTime getThoiGianBatDau() { return ThoiGianBatDau; }
    public void setThoiGianBatDau(LocalDateTime ThoiGianBatDau) { this.ThoiGianBatDau = ThoiGianBatDau; }

    public LocalDateTime getThoiGianKetThuc() { return ThoiGianKetThuc; }
    public void setThoiGianKetThuc(LocalDateTime ThoiGianKetThuc) { this.ThoiGianKetThuc = ThoiGianKetThuc; }

    public String getMoTa() { return MoTa; }
    public void setMoTa(String MoTa) { this.MoTa = MoTa; }
}