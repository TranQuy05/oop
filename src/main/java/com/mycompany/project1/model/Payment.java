package com.mycompany.project1.model;

import java.time.LocalDate;

public class Payment {
    private int paymentID;
    private int memberID;
    private int subscriptionID;
    private LocalDate paymentDate;
    private double price;
    private String type;
    private int ticketID;

    public Payment(int paymentID, int memberID, int subscriptionID, LocalDate paymentDate, double price, String type) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.subscriptionID = subscriptionID;
        this.paymentDate = paymentDate;
        this.price = price;
        this.type = type;
    }

    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }
    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }
    public int getSubscriptionID() { return subscriptionID; }
    public void setSubscriptionID(int subscriptionID) { this.subscriptionID = subscriptionID; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getTicketID() { return ticketID; }
    public void setTicketID(int ticketID) { this.ticketID = ticketID; }

    // Method: PayMembership
    public void payMembership() {
        // Logic thanh toán hội viên
    }

    // Method: Check validity (nếu liên quan đến thẻ)
    public boolean checkValidity() {
        // Logic kiểm tra tính hợp lệ của thanh toán hoặc thẻ
        return true;
    }

    // Method: Purchase card (nếu liên quan đến thẻ)
    public void purchaseCard() {
        // Logic mua thẻ
    }
} 