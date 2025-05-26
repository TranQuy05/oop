package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.PaymentDAO;
import com.mycompany.project1.model.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PaymentController {
    private ObservableList<Payment> danhSachPayment;
    private PaymentDAO paymentDAO;
    
    public PaymentController() {
        this.paymentDAO = new PaymentDAO();
        this.danhSachPayment = FXCollections.observableArrayList();
        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
        danhSachPayment.clear();
        danhSachPayment.addAll(paymentDAO.getAllPayments());
    }
    
    public boolean themPayment(Payment payment) {
        try {
            if (paymentDAO.themPayment(payment)) {
                danhSachPayment.add(payment);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm thanh toán: " + e.getMessage());
            return false;
        }
    }
    
    public ObservableList<Payment> timKiemPayment(String keyword) {
        ObservableList<Payment> ketQua = FXCollections.observableArrayList();
        for (Payment payment : danhSachPayment) {
            if (String.valueOf(payment.getPaymentID()).contains(keyword) ||
                String.valueOf(payment.getMemberID()).contains(keyword) ||
                String.valueOf(payment.getCardID()).contains(keyword)) {
                ketQua.add(payment);
            }
        }
        return ketQua;
    }
    
    public ObservableList<Payment> getDanhSachPayment() {
        return danhSachPayment;
    }
    
    public ObservableList<Payment> getPaymentsByMember(int memberID) {
        return paymentDAO.getPaymentsByMember(memberID);
    }
} 