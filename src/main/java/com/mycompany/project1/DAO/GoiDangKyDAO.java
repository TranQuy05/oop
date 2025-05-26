package com.mycompany.project1.DAO;

import com.mycompany.project1.model.GoiDangKy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GoiDangKyDAO {
    private Connection conn;
    
    public GoiDangKyDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<GoiDangKy> getAllGoiDangKy() {
        ObservableList<GoiDangKy> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Subscription ORDER BY SubscriptionID";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                GoiDangKy goi = new GoiDangKy();
                goi.setSubscriptionID(rs.getInt("SubscriptionID"));
                goi.setSubName(rs.getString("SubName"));
                goi.setType(rs.getString("Type"));
              
                goi.setStartDate(rs.getDate("StartDate").toLocalDate());
                goi.setSubscriptionDetail(rs.getString("SubscriptionDetail"));
                goi.setStatus(rs.getString("Status"));
                danhSach.add(goi);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách gói đăng ký: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public boolean themGoiDangKy(GoiDangKy goi) {
        String sql = "INSERT INTO Subscription (SubName, Type, StartDate, SubscriptionDetail, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goi.getSubName());
            stmt.setString(2, goi.getType());
          
            stmt.setDate(4, java.sql.Date.valueOf(goi.getStartDate()));
            stmt.setString(5, goi.getSubscriptionDetail());
            stmt.setString(6, goi.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean capNhatGoiDangKy(GoiDangKy goi) {
        String sql = "UPDATE Subscription SET SubName = ?, Type = ?,  " +
                    "StartDate = ?, SubscriptionDetail = ?, Status = ? WHERE SubscriptionID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goi.getSubName());
            stmt.setString(2, goi.getType());
          
            stmt.setDate(4, java.sql.Date.valueOf(goi.getStartDate()));
            stmt.setString(5, goi.getSubscriptionDetail());
            stmt.setString(6, goi.getStatus());
            stmt.setInt(7, goi.getSubscriptionID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean xoaGoiDangKy(int subscriptionID) {
        String sql = "DELETE FROM Subscription WHERE SubscriptionID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public GoiDangKy timGoiDangKyTheoMa(int subscriptionID) {
        String sql = "SELECT * FROM Subscription WHERE SubscriptionID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                GoiDangKy goi = new GoiDangKy();
                goi.setSubscriptionID(rs.getInt("SubscriptionID"));
                goi.setSubName(rs.getString("SubName"));
                goi.setType(rs.getString("Type"));
            
                goi.setStartDate(rs.getDate("StartDate").toLocalDate());
                goi.setSubscriptionDetail(rs.getString("SubscriptionDetail"));
                goi.setStatus(rs.getString("Status"));
                return goi;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 