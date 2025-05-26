package com.mycompany.project1.DAO;

import com.mycompany.project1.model.LopHoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LopHocDAO {
    private java.sql.Connection conn;

    public LopHocDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LopHoc> getDanhSachLopHoc() {
        ObservableList<LopHoc> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT c.*, u.UserName as TrainerName FROM Classs c " +
                    "LEFT JOIN Trainer t ON c.TrainerID = t.TrainerID " +
                    "LEFT JOIN Users u ON t.TrainerID = u.UserID";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                LopHoc lop = new LopHoc();
                lop.setClassID(rs.getInt("ClassID"));
                lop.setClassName(rs.getString("ClassName"));
                lop.setTrainerID(rs.getInt("TrainerID"));
                lop.setTrainerName(rs.getString("TrainerName"));
                lop.setSchedule(rs.getString("Schedule"));
                danhSach.add(lop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public ObservableList<String> getDanhSachHuongDanVien() {
        ObservableList<String> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT u.UserID, u.UserName FROM Users u " +
                    "JOIN Trainer t ON u.UserID = t.TrainerID " +
                    "WHERE u.Role = 'Trainer'";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                danhSach.add(rs.getString("UserName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public int getTrainerIDFromName(String trainerName) {
        String sql = "SELECT u.UserID FROM Users u " +
                    "JOIN Trainer t ON u.UserID = t.TrainerID " +
                    "WHERE u.UserName = ? AND u.Role = 'Trainer'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trainerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean themLopHoc(String tenLop, int trainerID, String lichHoc) {
        String sql = "INSERT INTO Classs (ClassName, TrainerID, Schedule) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenLop);
            stmt.setInt(2, trainerID);
            stmt.setString(3, lichHoc);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaLopHoc(int classID) {
        String sql = "DELETE FROM Classs WHERE ClassID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean capNhatLopHoc(LopHoc lop) {
        String sql = "UPDATE Classs SET ClassName = ?, TrainerID = ?, Schedule = ? WHERE ClassID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lop.getClassName());
            stmt.setInt(2, lop.getTrainerID());
            stmt.setString(3, lop.getSchedule());
            stmt.setInt(4, lop.getClassID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
} 