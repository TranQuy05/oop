package com.mycompany.project1.DAO;

import com.mycompany.project1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private java.sql.Connection conn;

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

    public boolean themUser(User user) {
        String sql = "INSERT INTO Users (UserName, UserEmail, Password, Role, Gender, UserYoB, Address, PhoneNum, CitizenIdentification) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            return stmt.executeUpdate() > 0;
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

    public User dangNhap(String email, String password) {
        String sql = "SELECT * FROM Users WHERE UserEmail = ? AND Password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
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
} 