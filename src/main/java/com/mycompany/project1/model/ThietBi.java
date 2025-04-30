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
public class ThietBi {
    private int ID;
    private String tenThietBi;
    private String loaiThietBi;
    private String status;
    private List<LocalDate> schedueleMaintenace;
    
    public ThietBi(int ID, String tenThietBi, String loaiThietBi, String status) {
        this.ID = ID;
        this.tenThietBi = tenThietBi;
        this.loaiThietBi = loaiThietBi;
        this.status = status;
    }

    // Getter và Setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getLoaiThietBi() {
        return loaiThietBi;
    }

    public void setLoaiThietBi(String loaiThietBi) {
        this.loaiThietBi = loaiThietBi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     public List<LocalDate> getSchedueleMaintenace() {
        return schedueleMaintenace;
    }

    public void setSchedueleMaintenace(List<LocalDate> schedueleMaintenace) {
        this.schedueleMaintenace = schedueleMaintenace;
    }
     public void scheduleMaintenance() {
        LocalDate ngayHienTai = LocalDate.now();
        schedueleMaintenace.add(ngayHienTai);
        this.status = "Bảo trì";
        System.out.println("Thiết bị '" + tenThietBi + "' đã được bảo trì vào ngày " + ngayHienTai);
    }

    // Lấy lần bảo trì gần nhất
    public LocalDate getNgayBaoTriGanNhat() {
        if (schedueleMaintenace.isEmpty()) {
            return null;
        }
        return schedueleMaintenace.get(schedueleMaintenace.size() - 1);
    }

    // In toàn bộ lịch sử bảo trì
    public void inLichSuBaoTri() {
        System.out.println("Lịch sử bảo trì của thiết bị: " + tenThietBi);
        if (schedueleMaintenace.isEmpty()) {
            System.out.println("Chưa có lần bảo trì nào.");
        } else {
            for (LocalDate date : schedueleMaintenace) {
                System.out.println("- " + date);
            }
        }
    }
    
}
