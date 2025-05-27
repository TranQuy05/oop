package com.mycompany.project1.model;

import java.time.LocalDate;

public class Payment {
    private int paymentID;
    private int memberID;
    private int subscriptionID;
    private int cardID;
    private LocalDate paymentDate;
    private String type;
    private String subscriptionName;
    private String cardType;

    public Payment() {
    }

    public Payment(int paymentID, int memberID, int subscriptionID, int cardID, LocalDate paymentDate, String type) {
        this.paymentID = paymentID;
        this.memberID = memberID;
        this.subscriptionID = subscriptionID;
        this.cardID = cardID;
        this.paymentDate = paymentDate;
        this.type = type;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void payMembership() {
    }


    public boolean checkValidity() {
        
        return true;
    }

 
    public void purchaseCard() {

    }
} 