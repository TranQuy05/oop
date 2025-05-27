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
    // lấy tát cả gói đăng ký từ dâtbase
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
    // thêm gói đăng ký mới
    public boolean themGoiDangKy(GoiDangKy goi) {
        // Lấy mã gói cuối cùng và tăng thêm 1
        int lastID = getLastSubscriptionID();
        int newID = lastID + 1;
        
        String sql = "INSERT INTO Subscription (SubscriptionID, SubName, Type, StartDate, SubscriptionDetail, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newID);
            stmt.setString(2, goi.getSubName());
            stmt.setString(3, goi.getType());
            stmt.setDate(4, java.sql.Date.valueOf(goi.getStartDate()));
            stmt.setString(5, goi.getSubscriptionDetail());
            stmt.setString(6, goi.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                goi.setSubscriptionID(newID); // Cập nhật mã gói mới cho đối tượng
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // hàm cập nhật gói đăng ký 
    public boolean capNhatGoiDangKy(GoiDangKy goi) {
        String sql = "UPDATE Subscription SET SubName = ?, Type = ?, " +
                    "StartDate = ?, SubscriptionDetail = ?, Status = ? WHERE SubscriptionID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goi.getSubName());
            stmt.setString(2, goi.getType());
            stmt.setDate(3, java.sql.Date.valueOf(goi.getStartDate()));
            stmt.setString(4, goi.getSubscriptionDetail());
            stmt.setString(5, goi.getStatus());
            stmt.setInt(6, goi.getSubscriptionID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // hàm xóa gói đăng ký 
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
    // hàm tìm gói đăng ký theo mã 
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
    // Thêm phương thức này vào GoiDangKyDAO.java
    public int getLastSubscriptionID() {
        String sql = "SELECT MAX(SubscriptionID) as LastID FROM Subscription";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("LastID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu chưa có gói nào
    }
} 