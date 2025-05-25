package com.mycompany.project1.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối database thành công!");
            } else {
                System.out.println("Kết nối database thất bại!");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối database: " + e.getMessage());
        }
    }
}