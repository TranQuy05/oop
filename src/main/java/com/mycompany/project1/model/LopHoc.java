package com.mycompany.project1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LopHoc {
    private int classID;
    private String className;
    private String trainerID;
    private LocalDate schedule; 

 
    private List<String> memberIDs;

    public LopHoc(int classID, String className, String trainerID, LocalDate schedule) {
        this.classID = classID;
        this.className = className;
        this.trainerID = trainerID;
        this.schedule = schedule;
        this.memberIDs = new ArrayList<>();
    }

    // Getter và Setter
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
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
        this.className = className;
        this.trainerID = trainerID;
        this.schedule = schedule;
    }


    public void viewMemberships() {
        System.out.println("Danh sách hội viên của lớp " + className + ":");
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
