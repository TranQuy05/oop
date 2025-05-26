package com.mycompany.project1.DAO;

import com.mycompany.project1.model.HoiVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HoiVienDAO {
    private Connection connection;
    
    public HoiVienDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
    }
    
    // Lấy tất cả hội viên từ database
    public List<HoiVien> getAllHoiVien() {
        List<HoiVien> danhSachHoiVien = new ArrayList<>();
        String query = "SELECT * FROM Member";
        
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                HoiVien hoiVien = new HoiVien(
                    rs.getInt("MemberID"),           // Mã hội viên
                    rs.getString("MemberName"),      // Họ tên
                    rs.getInt("MemberYob"),          // Năm sinh
                    rs.getInt("MemberPhoneNumber"),  // Số điện thoại
                    rs.getString("Gender"),          // Giới tính
                    rs.getString("Address"),         // Địa chỉ
                    rs.getString("Email"),           // Email
                    rs.getString("MemberCitizenIden") // Căn cước công dân
                );
                danhSachHoiVien.add(hoiVien);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách hội viên: " + e.getMessage());
        }
        
        return danhSachHoiVien;
    }
    
    // Thêm hội viên mới vào database
    public boolean themHoiVien(HoiVien hoiVien) {
        String query = "INSERT INTO Member (MemberName, MemberYob, MemberPhoneNumber, Gender, Address, Email, MemberCitizenIden) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoiVien.getHoTen());
            stmt.setInt(2, hoiVien.getNamSinh());
            stmt.setInt(3, hoiVien.getSdt());
            stmt.setString(4, hoiVien.getGioiTinh());
            stmt.setString(5, hoiVien.getDiaChi());
            stmt.setString(6, hoiVien.getEmail());
            stmt.setString(7, hoiVien.getCanCuocCongDan());
            
            int result = stmt.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result); // Debug log
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm hội viên: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
            return false;
        }
    }
    
    // Tìm hội viên theo mã
    public HoiVien timHoiVienTheoMa(int maHV) {
        String query = "SELECT * FROM Member WHERE MemberID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, maHV);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new HoiVien(
                    rs.getInt("MemberID"),           // Mã hội viên
                    rs.getString("MemberName"),      // Họ tên
                    rs.getInt("MemberYob"),          // Năm sinh
                    rs.getInt("MemberPhoneNumber"),  // Số điện thoại
                    rs.getString("Gender"),          // Giới tính
                    rs.getString("Address"),         // Địa chỉ
                    rs.getString("Email"),           // Email
                    rs.getString("MemberCitizenIden") // Căn cước công dân
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm hội viên: " + e.getMessage());
        }
        
        return null;
    }
    
    // Tìm hội viên theo mã thẻ
    public HoiVien timHoiVienBangMaThe(String maThe) {
        String query = "SELECT * FROM Member WHERE MemberCardID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maThe);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new HoiVien(
                    rs.getInt("MemberID"),
                    rs.getString("MemberName"),
                    rs.getInt("MemberYob"),
                    rs.getInt("MemberPhoneNumber"),
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Email"),
                    rs.getString("MemberCitizenIden")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm hội viên theo mã thẻ: " + e.getMessage());
        }
        
        return null;
    }
    
    // Cập nhật thông tin hội viên
    public boolean capNhatHoiVien(HoiVien hoiVien) {
        String query = "UPDATE Member SET MemberName = ?, MemberYob = ?, MemberPhoneNumber = ?, " +
                      "Gender = ?, Address = ?, Email = ?, MemberCitizenIden = ? " +
                      "WHERE MemberID = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoiVien.getHoTen());
            stmt.setInt(2, hoiVien.getNamSinh());
            stmt.setInt(3, hoiVien.getSdt());
            stmt.setString(4, hoiVien.getGioiTinh());
            stmt.setString(5, hoiVien.getDiaChi());
            stmt.setString(6, hoiVien.getEmail());
            stmt.setString(7, hoiVien.getCanCuocCongDan());
            stmt.setInt(8, hoiVien.getMaHoiVien());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật hội viên: " + e.getMessage());
            return false;
        }
    }
    
    // Xóa hội viên
    public boolean xoaHoiVien(int maHV) {
        try {
            // Bắt đầu transaction
            connection.setAutoCommit(false);
            
            try {
                // Xóa các bản ghi liên quan trong bảng payment
                String deletePaymentQuery = "DELETE FROM Payment WHERE MemberID = ?";
                try (PreparedStatement stmt = connection.prepareStatement(deletePaymentQuery)) {
                    stmt.setInt(1, maHV);
                    stmt.executeUpdate();
                }
                
                // Xóa các bản ghi liên quan trong bảng TrainingLog
                String deleteTrainingLogQuery = "DELETE FROM TrainingLog WHERE MemberID = ?";
                try (PreparedStatement stmt = connection.prepareStatement(deleteTrainingLogQuery)) {
                    stmt.setInt(1, maHV);
                    stmt.executeUpdate();
                }
                
                // Xóa hội viên
                String deleteMemberQuery = "DELETE FROM Member WHERE MemberID = ?";
                try (PreparedStatement stmt = connection.prepareStatement(deleteMemberQuery)) {
                    stmt.setInt(1, maHV);
                    int result = stmt.executeUpdate();
                    
                    if (result > 0) {
                        connection.commit();
                        return true;
                    }
                }
                
                // Nếu không xóa được, rollback
                connection.rollback();
                return false;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa hội viên: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra xem hội viên đã check-in trong ngày chưa
    public boolean kiemTraCheckIn(int maHV, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM TrainingLog WHERE MemberID = ? AND TrainingDate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maHV);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thực hiện check-in cho hội viên
    public boolean checkInHoiVien(int maHV, LocalDate date, LocalTime time) {
        String sql = "INSERT INTO TrainingLog (MemberID, TrainingDate, CheckInTime, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maHV);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            stmt.setTime(3, java.sql.Time.valueOf(time));
            stmt.setString(4, "Đang tập");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách hội viên đã check-in trong ngày
    public ObservableList<HoiVien> getDanhSachHoiVienCheckIn(LocalDate date) {
        ObservableList<HoiVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT m.*, t.Status, t.CheckInTime " +
                    "FROM Member m " +
                    "JOIN TrainingLog t ON m.MemberID = t.MemberID " +
                    "WHERE t.TrainingDate = ? " +
                    "ORDER BY t.CheckInTime DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoiVien hv = new HoiVien(
                    rs.getInt("MemberID"),
                    rs.getString("MemberName"),
                    rs.getInt("MemberYob"),
                    rs.getInt("MemberPhoneNumber"),
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Email"),
                    rs.getString("MemberCitizenIden")
                );
                hv.setTrangThai(rs.getString("Status"));
                hv.setThoiGianCheckIn(rs.getTime("CheckInTime").toString());
                danhSach.add(hv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Lấy số lần tập của hội viên
    public int getSoLanTap(int maHV, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM CheckIn WHERE MemberID = ? AND CheckInDate BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maHV);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy số hội viên tập theo ngày
    public int getSoHoiVienTapTheoNgay(LocalDate date) {
        String sql = "SELECT COUNT(DISTINCT MemberID) FROM TrainingLog WHERE TrainingDate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy số hội viên tập theo tuần
    public int getSoHoiVienTapTheoTuan(LocalDate startDate) {
        String sql = "SELECT COUNT(DISTINCT MemberID) FROM TrainingLog WHERE TrainingDate BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(startDate.plusDays(6)));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy số hội viên tập theo tháng
    public int getSoHoiVienTapTheoThang(LocalDate startDate) {
        String sql = "SELECT COUNT(DISTINCT MemberID) FROM TrainingLog WHERE TrainingDate BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(startDate.plusMonths(1).minusDays(1)));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy lịch sử tập luyện của hội viên
    public ObservableList<HoiVien> getLichSuTapLuyen(int maHV, LocalDate startDate, LocalDate endDate) {
        ObservableList<HoiVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT h.*, c.CheckInDate, c.CheckInTime FROM HoiVien h " +
                    "JOIN CheckIn c ON h.MemberID = c.MemberID " +
                    "WHERE h.MemberID = ? AND c.CheckInDate BETWEEN ? AND ? " +
                    "ORDER BY c.CheckInDate DESC, c.CheckInTime DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maHV);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoiVien hv = new HoiVien(
                    rs.getInt("MemberID"),
                    rs.getString("FullName"),
                    rs.getInt("BirthYear"),
                    rs.getInt("PhoneNumber"),
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Email"),
                    rs.getString("IDCard")
                );
                hv.setNgayTap(rs.getDate("CheckInDate").toLocalDate());
                hv.setThoiGianCheckIn(rs.getTime("CheckInTime").toLocalTime().toString());
                danhSach.add(hv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Lấy lịch sử tập luyện của tất cả hội viên
    public ObservableList<HoiVien> getLichSuTapLuyen(LocalDate startDate, LocalDate endDate) {
        ObservableList<HoiVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT m.*, t.TrainingDate, t.CheckInTime, t.Status " +
                    "FROM Member m " +
                    "JOIN TrainingLog t ON m.MemberID = t.MemberID " +
                    "WHERE t.TrainingDate BETWEEN ? AND ? " +
                    "ORDER BY t.TrainingDate DESC, t.CheckInTime DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoiVien hv = new HoiVien(
                    rs.getInt("MemberID"),
                    rs.getString("MemberName"),
                    rs.getInt("MemberYob"),
                    rs.getInt("MemberPhoneNumber"),
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Email"),
                    rs.getString("MemberCitizenIden")
                );
                hv.setNgayTap(rs.getDate("TrainingDate").toLocalDate());
                hv.setThoiGianCheckIn(rs.getTime("CheckInTime").toString());
                hv.setTrangThai(rs.getString("Status"));
                danhSach.add(hv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public boolean checkOutHoiVien(int maHoiVien) {
        try {
            String sql = "UPDATE TrainingLog SET Status = 'Đã ra về' WHERE MemberID = ? AND Status = 'Đang tập' AND TrainingDate = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, maHoiVien);
                stmt.setDate(2, Date.valueOf(LocalDate.now()));
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTongSoHoiVien() {
        String sql = "SELECT COUNT(*) FROM Member";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
