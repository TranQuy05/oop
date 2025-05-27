package com.mycompany.project1.DAO;

import com.mycompany.project1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> getDanhSachUser() {
        ObservableList<User> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Users";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setRole(rs.getString("Role"));
                user.setGender(rs.getString("Gender"));
                user.setUserYoB(rs.getInt("UserYoB"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNum(rs.getString("PhoneNum"));
                user.setCitizenIdentification(rs.getString("CitizenIdentification"));
                danhSach.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public User getUserByID(int userID) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setRole(rs.getString("Role"));
                user.setGender(rs.getString("Gender"));
                user.setUserYoB(rs.getInt("UserYoB"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNum(rs.getString("PhoneNum"));
                user.setCitizenIdentification(rs.getString("CitizenIdentification"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // lấy mã nhân viên ở cuối danh sách
    public int getLastUserID() {
        String sql = "SELECT MAX(UserID) as LastID FROM Users";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("LastID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu chưa có nhân viên nào
    }

    public boolean themUser(User user) {
        // lấy mã nhân viên cuối danh sách , tự tăng thêm 1 = mã nhân viên mới
        int lastID = getLastUserID();
        int newID = lastID + 1;
        
        String sql = "INSERT INTO Users (UserID, UserName, UserEmail, Password, Role, Gender, UserYoB, Address, PhoneNum, CitizenIdentification) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newID);
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getUserEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getGender());
            stmt.setInt(7, user.getUserYoB());
            stmt.setString(8, user.getAddress());
            stmt.setString(9, user.getPhoneNum());
            stmt.setString(10, user.getCitizenIdentification());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                user.setUserID(newID);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean capNhatUser(User user) {
        String sql = "UPDATE Users SET UserName = ?, UserEmail = ?, Password = ?, Role = ?, " +
                    "Gender = ?, UserYoB = ?, Address = ?, PhoneNum = ?, CitizenIdentification = ? " +
                    "WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getGender());
            stmt.setInt(6, user.getUserYoB());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getPhoneNum());
            stmt.setString(9, user.getCitizenIdentification());
            stmt.setInt(10, user.getUserID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaUser(int userID) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        String query = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(); // Trả về User mới nếu đăng nhập thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }
} 