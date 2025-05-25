package com.mycompany.project1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LopHoc {
    private int maLop;
    private String TenLop;
    private String maHLV;
    private LocalDate schedule; 

 
    private List<String> memberIDs;

    public LopHoc(int maLop, String TenLop, String maNhanVien, LocalDate schedule) {
        this.maLop = maLop;
        this.TenLop = TenLop;
        this.maHLV = maHLV;
        this.schedule = schedule;
        this.memberIDs = new ArrayList<>();
    }

    // Getter và Setter
    public int getClassID() {
        return maLop;
    }

    public void setClassID(int maLop) {
        this.maLop = maLop;
    }

    public String getClassName() {
        return TenLop;
    }

    public void setClassName(String TenLop) {
        this.TenLop = TenLop;
    }

    public String getTrainerID() {
        return maHLV;
    }

    public void setTrainerID(String maHLV) {
        this.maHLV = maHLV;
    }

    public LocalDate getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDate schedule) {
        this.schedule = schedule;
    }

    public List<String> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(List<String> memberIDs) {
        this.memberIDs = memberIDs;
    }

   
    public static LopHoc addClass(int classID, String className, String trainerID, LocalDate schedule) {
        return new LopHoc(classID, className, trainerID, schedule);
    }

    // ✅ Method: Cập nhật thông tin lớp
    public void updateClass(String className, String trainerID, LocalDate schedule) {
        this.TenLop = className;
        this.maHLV = trainerID;
        this.schedule = schedule;
    }


    public void viewMemberships() {
        System.out.println("Danh sách hội viên của lớp " + TenLop + ":");
        if (memberIDs.isEmpty()) {
            System.out.println("Chưa có hội viên đăng ký.");
        } else {
            for (String memberID : memberIDs) {
                System.out.println("- " + memberID);
            }
        }
    }

   
    public void addMember(String memberID) {
        if (!memberIDs.contains(memberID)) {
            memberIDs.add(memberID);
        }
    }

    // Optional: Xóa hội viên
    public void removeMember(String memberID) {
        memberIDs.remove(memberID);
    }
}
