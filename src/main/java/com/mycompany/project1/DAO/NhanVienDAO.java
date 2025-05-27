package com.mycompany.project1.DAO;

import com.mycompany.project1.model.NhanVien;
import com.mycompany.project1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO {
    private Connection conn;
    private UserDAO userDAO;
    
    public NhanVienDAO() {
        try {
            this.conn = DBConnection.getConnection();
            this.userDAO = new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<NhanVien> getAllNhanVien() {
        ObservableList<NhanVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT u.UserID AS StaffID, " +
                    "u.UserName, " +
                    "u.UserEmail, " +
                    "u.Role, " +
                    "u.Gender, " +
                    "u.UserYoB, " +
                    "u.Address, " +
                    "u.PhoneNum, " +
                    "u.CitizenIdentification, " +
                    "r.StartWorkingDate " +
                    "FROM Users u " +
                    "LEFT JOIN Receptionist r ON u.UserID = r.StaffID " +
                    "WHERE u.Role IN ('Receptionist', 'Trainer') " +
                    "ORDER BY u.UserID";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            
            while (rs.next()) {
         
                User user = new User();
                user.setUserID(rs.getInt("StaffID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setRole(rs.getString("Role"));
                user.setGender(rs.getString("Gender"));
                user.setUserYoB(rs.getInt("UserYoB"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNum(rs.getString("PhoneNum"));
                user.setCitizenIdentification(rs.getString("CitizenIdentification"));

                NhanVien nv = new NhanVien();
                nv.setStaffID(rs.getInt("StaffID"));
                nv.setStartWorkingDate(rs.getString("StartWorkingDate"));
                nv.setUser(user);
                danhSach.add(nv);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public boolean themNhanVien(NhanVien nv) {
        // Đầu tiên thêm vào bảng Users
        if (!userDAO.themUser(nv.getUser())) {
            return false;
        }

        // Sau đó thêm vào bảng Receptionist với StaffID là UserID vừa được tạo
        String sql = "INSERT INTO Receptionist (StaffID, StartWorkingDate) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nv.getUser().getUserID()); // Sử dụng UserID đã được tự động tăng
            stmt.setString(2, nv.getStartWorkingDate());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean capNhatNhanVien(NhanVien nv) {
        // Cập nhật thông tin trong bảng Users
        if (!userDAO.capNhatUser(nv.getUser())) {
            return false;
        }

        // Cập nhật thông tin trong bảng Receptionist
        String sql = "UPDATE Receptionist SET StartWorkingDate = ? WHERE StaffID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nv.getStartWorkingDate());
            stmt.setInt(2, nv.getStaffID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean xoaNhanVien(int staffID) {
        // Xóa từ bảng Receptionist
        String sql = "DELETE FROM Receptionist WHERE StaffID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, staffID);
            if (stmt.executeUpdate() > 0) {
                // Nếu xóa thành công từ Receptionist, xóa tiếp từ Users
                return userDAO.xoaUser(staffID);
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public NhanVien timNhanVienTheoMa(int maNV) {
        String sql = "SELECT u.UserID AS StaffID, " +
                    "u.UserName, " +
                    "u.UserEmail, " +
                    "u.Role, " +
                    "u.Gender, " +
                    "u.UserYoB, " +
                    "u.Address, " +
                    "u.PhoneNum, " +
                    "u.CitizenIdentification, " +
                    "r.StartWorkingDate " +
                    "FROM Users u " +
                    "LEFT JOIN Receptionist r ON u.UserID = r.StaffID " +
                    "WHERE u.UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("StaffID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setRole(rs.getString("Role"));
                user.setGender(rs.getString("Gender"));
                user.setUserYoB(rs.getInt("UserYoB"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNum(rs.getString("PhoneNum"));
                user.setCitizenIdentification(rs.getString("CitizenIdentification"));

                NhanVien nv = new NhanVien();
                nv.setStaffID(rs.getInt("StaffID"));
                nv.setStartWorkingDate(rs.getString("StartWorkingDate"));
                nv.setUser(user);
                return nv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 