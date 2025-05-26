package com.mycompany.project1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LopHoc {
    private int classID;
    private String className;
    private int trainerID;
    private String trainerName;
    private String schedule;

    private List<String> memberIDs;

    public LopHoc() {
    }

    public LopHoc(int classID, String className, int trainerID, String trainerName, String schedule) {
        this.classID = classID;
        this.className = className;
        this.trainerID = trainerID;
        this.trainerName = trainerName;
        this.schedule = schedule;
        this.memberIDs = new ArrayList<>();
    }

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

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<String> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(List<String> memberIDs) {
        this.memberIDs = memberIDs;
    }

    public static LopHoc addClass(int classID, String className, int trainerID, String trainerName, String schedule) {
        return new LopHoc(classID, className, trainerID, trainerName, schedule);
    }

    public void updateClass(String className, int trainerID, String trainerName, String schedule) {
        this.className = className;
        this.trainerID = trainerID;
        this.trainerName = trainerName;
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

    public void removeMember(String memberID) {
        memberIDs.remove(memberID);
    }
}
