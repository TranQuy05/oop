package com.mycompany.project1.DAO;

import com.mycompany.project1.model.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentDAO {
    private Connection conn;
    
    public PaymentDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<Payment> getAllPayments() {
        ObservableList<Payment> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Payment ORDER BY PaymentDate DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setMemberID(rs.getInt("MemberID"));
                payment.setSubscriptionID(rs.getInt("SubscriptionID"));
                payment.setCardID(rs.getInt("CardID"));
                payment.setPaymentDate(rs.getDate("PaymentDate").toLocalDate());
                payment.setType(rs.getString("Type"));
                danhSach.add(payment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách thanh toán: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public boolean themPayment(Payment payment) {
        String sql = "INSERT INTO Payment (MemberID, SubscriptionID, CardID, PaymentDate, Type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getMemberID());
            stmt.setInt(2, payment.getSubscriptionID());
            stmt.setInt(3, payment.getCardID());
            stmt.setDate(4, java.sql.Date.valueOf(payment.getPaymentDate()));
            stmt.setString(5, payment.getType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public double tinhTongDoanhThu(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT SUM(Amount) FROM Payment WHERE PaymentDate BETWEEN ? AND ? AND Status = 'PAID'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public ObservableList<Payment> getPaymentsByMember(int memberID) {
        ObservableList<Payment> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Payment WHERE MemberID = ? ORDER BY PaymentDate DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setMemberID(rs.getInt("MemberID"));
                payment.setSubscriptionID(rs.getInt("SubscriptionID"));
                payment.setCardID(rs.getInt("CardID"));
                payment.setPaymentDate(rs.getDate("PaymentDate").toLocalDate());
                payment.setType(rs.getString("Type"));
                danhSach.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public ObservableList<Payment> getPaymentsByTrainer(int trainerID) {
        ObservableList<Payment> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Payment WHERE TrainerID = ? ORDER BY PaymentDate DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trainerID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setMemberID(rs.getInt("MemberID"));
                payment.setSubscriptionID(rs.getInt("SubscriptionID"));
                payment.setPaymentDate(rs.getDate("PaymentDate").toLocalDate());
              //  payment.setAmount(rs.getDouble("Amount"));
                payment.setType(rs.getString("PaymentType"));
              //  payment.setStatus(rs.getString("Status"));

                danhSach.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
} 