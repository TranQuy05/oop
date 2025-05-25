package com.mycompany.project1.model;

import java.time.LocalDate;

public class LopHoc_HLV {
    private int LopHoc_HLVID;
    private int maHLV;
    private int maLop;
    private LocalDate lichPhanCong; // 
    private String trangThai; // lớp đang mở hay vẫn đang chờ xếp lịch

    public LopHoc_HLV(int LopHoc_HLVID, int maHLV, int maLop, LocalDate lichPhanCong, String trangThai) {
        this.LopHoc_HLVID = LopHoc_HLVID;
        this.maHLV = maHLV;
        this.maLop = maLop;
        this.lichPhanCong = lichPhanCong;
        this.trangThai = trangThai;
    }

    public int getLopHoc_HLVID() { return LopHoc_HLVID; }
    public void setLopHoc_HLVID(int LopHoc_HLVID) { this.LopHoc_HLVID = LopHoc_HLVID; }
    public int getMaHLV() { return maHLV; }
    public void setMaHLV(int maHLV) { this.maHLV = maHLV; }
    public int getMaLop() { return maLop; }
    public void setMaLop(int maLop) { this.maLop = maLop; }
    public LocalDate getLichPhanCong() { return lichPhanCong; }
    public void setLichPhanCong(LocalDate lichPhanCong) { this.lichPhanCong = lichPhanCong; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    // Methods
    public void phanCongHLV() {
        // Logic phân công HLV cho lớp
    }
    public void xoaHLV() {
        // Logic xóa HLV khỏi lớp
    }
    public void layThongTinHLV() {
        // Logic lấy thông tin HLV từ lớp
    }
    public void capNhatTrangThai() {
        // Logic cập nhật trạng thái
    }
} 