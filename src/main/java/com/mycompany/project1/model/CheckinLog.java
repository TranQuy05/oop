package com.mycompany.project1.model;

import java.time.LocalDateTime;

public class CheckinLog {
    private int attendanceID;
    private int memberID;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime; // Có thể null nếu chưa checkout
    private int staffID; // Người xác nhận (nếu có)

    public CheckinLog(int attendanceID, int memberID, LocalDateTime checkinTime, LocalDateTime checkoutTime, int staffID) {
        this.attendanceID = attendanceID;
        this.memberID = memberID;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.staffID = staffID;
    }

    // Getter & Setter
    public int getAttendanceID() { return attendanceID; }
    public void setAttendanceID(int attendanceID) { this.attendanceID = attendanceID; }

    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }

    public LocalDateTime getCheckinTime() { return checkinTime; }
    public void setCheckinTime(LocalDateTime checkinTime) { this.checkinTime = checkinTime; }

    public LocalDateTime getCheckoutTime() { return checkoutTime; }
    public void setCheckoutTime(LocalDateTime checkoutTime) { this.checkoutTime = checkoutTime; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    // Các phương thức nghiệp vụ
    public void checkIn() {
        this.checkinTime = LocalDateTime.now();
    }

    public void checkOut() {
        this.checkoutTime = LocalDateTime.now();
    }
}