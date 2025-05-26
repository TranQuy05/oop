/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.model;

import java.time.LocalDate;

/**
 *
 * @author dtquy
 */
public class GoiDangKy {
    private int subscriptionID;
    private String subName;
    private String type;
    private LocalDate startDate;
    private String subscriptionDetail;
    private String status;

    public GoiDangKy() {
    }

    public GoiDangKy(int subscriptionID, String subName, String type, LocalDate startDate, String subscriptionDetail, String status) {
        this.subscriptionID = subscriptionID;
        this.subName = subName;
        this.type = type;
        this.startDate = startDate;
        this.subscriptionDetail = subscriptionDetail;
        this.status = status;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getSubscriptionDetail() {
        return subscriptionDetail;
    }

    public void setSubscriptionDetail(String subscriptionDetail) {
        this.subscriptionDetail = subscriptionDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
