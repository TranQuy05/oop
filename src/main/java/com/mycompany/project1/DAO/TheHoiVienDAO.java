package com.mycompany.project1.DAO;

import com.mycompany.project1.model.TheHoiVien;
import com.mycompany.project1.model.GoiDangKy;
import com.mycompany.project1.model.Payment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TheHoiVienDAO {
    private java.sql.Connection conn;
    
    public TheHoiVienDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TheHoiVien layTheTheoMa(int maThe) {
        String sql = "SELECT * FROM Card WHERE CardID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maThe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TheHoiVien the = new TheHoiVien();
                the.setCardID(rs.getInt("CardID"));
                the.setType(rs.getString("Type"));
                the.setPrice(rs.getDouble("Price"));
                the.setValidDuration(rs.getInt("ValidDuration"));
                
                // Lấy danh sách gói đăng ký của thẻ
                List<GoiDangKy> danhSachGoi = layDanhSachGoiDangKy(the.getCardID());
                the.setDanhSachGoi(danhSachGoi);
                
                return the;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TheHoiVien layTheTheoLoai(String loaiThe) {
        String sql = "SELECT * FROM Card WHERE Type = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loaiThe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TheHoiVien the = new TheHoiVien();
                the.setCardID(rs.getInt("CardID"));
                the.setType(rs.getString("Type"));
                the.setPrice(rs.getDouble("Price"));
                the.setValidDuration(rs.getInt("ValidDuration"));
                return the;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GoiDangKy> layDanhSachGoiDangKy(int maThe) {
        List<GoiDangKy> danhSachGoi = new ArrayList<>();
        String sql = "SELECT * FROM Subscription WHERE CardID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maThe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GoiDangKy goi = new GoiDangKy();
                goi.setSubscriptionID(rs.getInt("SubscriptionID"));
                goi.setSubName(rs.getString("SubName"));
                goi.setType(rs.getString("Type"));
                goi.setStartDate(rs.getDate("StartDate").toLocalDate());
                goi.setSubscriptionDetail(rs.getString("SubscriptionDetail"));
                goi.setStatus(rs.getString("Status"));
                danhSachGoi.add(goi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachGoi;
    }

    public boolean themThe(TheHoiVien the, int memberID) {
        String queryCard = "INSERT INTO Card (CardID, Type, Price, ValidDuration) VALUES (?, ?, ?, ?)";
        String queryMemberCard = "INSERT INTO Member_Card (MemberID, CardID, PurchaseDate) VALUES (?, ?, ?)";
        String querySubscription = "INSERT INTO Subscription (SubName, Type, StartDate, SubscriptionDetail, Status) VALUES (?, ?, ?, ?, ?)";
        String queryPayment = "INSERT INTO Payment (MemberID, SubscriptionID, CardID, PaymentDate, Type) VALUES (?, ?, ?, ?, ?)";
        
        try {
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            // 1. Thêm thẻ mới vào bảng Card
            int newCardID = generateNewCardID(the.getType());
            try (PreparedStatement stmt = conn.prepareStatement(queryCard)) {
                stmt.setInt(1, newCardID);
            stmt.setString(2, the.getType());
            stmt.setDouble(3, the.getPrice());
            stmt.setInt(4, the.getValidDuration());
                
                if (stmt.executeUpdate() > 0) {
                    the.setCardID(newCardID);
                    
                    // 2. Thêm liên kết vào bảng Member_Card
                    try (PreparedStatement stmtMemberCard = conn.prepareStatement(queryMemberCard)) {
                        stmtMemberCard.setInt(1, memberID);
                        stmtMemberCard.setInt(2, newCardID);
                        stmtMemberCard.setDate(3, Date.valueOf(LocalDate.now()));
                        
                        if (stmtMemberCard.executeUpdate() > 0) {
                            // 3. Tạo gói đăng ký mới
                            try (PreparedStatement stmtSubscription = conn.prepareStatement(querySubscription, Statement.RETURN_GENERATED_KEYS)) {
                                stmtSubscription.setString(1, the.getType() + " Package");
                                stmtSubscription.setString(2, "Monthly");
                                stmtSubscription.setDate(3, Date.valueOf(LocalDate.now()));
                                stmtSubscription.setString(4, "Standard package for " + the.getType() + " card");
                                stmtSubscription.setString(5, "Active");
                                
                                if (stmtSubscription.executeUpdate() > 0) {
                                    // 4. Lấy SubscriptionID vừa tạo và thêm thanh toán
                                    try (ResultSet rs = stmtSubscription.getGeneratedKeys()) {
                                        if (rs.next()) {
                                            int subscriptionID = rs.getInt(1);
                                            
                                            // 5. Thêm thanh toán
                                            try (PreparedStatement stmtPayment = conn.prepareStatement(queryPayment)) {
                                                stmtPayment.setInt(1, memberID);
                                                stmtPayment.setInt(2, subscriptionID);
                                                stmtPayment.setInt(3, newCardID);
                                                stmtPayment.setDate(4, Date.valueOf(LocalDate.now()));
                                                stmtPayment.setString(5, "Cash");
                                                
                                                if (stmtPayment.executeUpdate() > 0) {
                                                    conn.commit(); // Commit transaction nếu thành công
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            conn.rollback(); // Rollback nếu có lỗi
            return false;
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi khi thêm thẻ: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true); // Kết thúc transaction
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean themGoiDangKy(int maThe, GoiDangKy goi) {
        String sql = "INSERT INTO Subscription (SubName, Type, StartDate, SubscriptionDetail, Status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, goi.getSubName());
            stmt.setString(2, goi.getType());
            stmt.setDate(3, Date.valueOf(goi.getStartDate()));
            stmt.setString(4, goi.getSubscriptionDetail());
            stmt.setString(5, goi.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách thẻ sắp hết hạn
    public ObservableList<TheHoiVien> layDanhSachTheSapHetHan(int soNgay) {
        ObservableList<TheHoiVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Card WHERE ValidDuration <= ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, soNgay);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheHoiVien the = new TheHoiVien(
                    rs.getInt("CardID"),
                    rs.getString("Type"),
                    rs.getDouble("Price"),
                    rs.getInt("ValidDuration")
                );
                danhSach.add(the);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public int getLastCardID() {
        String query = "SELECT CardID FROM Card ORDER BY CardID DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("CardID");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy mã thẻ cuối: " + e.getMessage());
        }
        return 0;
    }

    public int generateNewCardID(String cardType) {
        String prefix = getPrefixForCardType(cardType);
        int lastCardID = getLastCardID();
        
        if (lastCardID == 0 || !String.valueOf(lastCardID).startsWith(prefix)) {
            return Integer.parseInt(prefix + "001");
        }
        
        String sequence = String.valueOf(lastCardID).substring(prefix.length());
        int nextSequence = Integer.parseInt(sequence) + 1;
        return Integer.parseInt(prefix + String.format("%03d", nextSequence));
    }

    public ObservableList<String> getCardTypes() {
        ObservableList<String> cardTypes = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT Type FROM Card";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cardTypes.add(rs.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardTypes;
    }

    public ObservableList<TheHoiVien> getAllCards() {
        ObservableList<TheHoiVien> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Card";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TheHoiVien the = new TheHoiVien();
                the.setCardID(rs.getInt("CardID"));
                the.setType(rs.getString("Type"));
                the.setPrice(rs.getDouble("Price"));
                the.setValidDuration(rs.getInt("ValidDuration"));
                
                // Lấy danh sách gói đăng ký của thẻ
                List<GoiDangKy> danhSachGoi = layDanhSachGoiDangKy(the.getCardID());
                the.setDanhSachGoi(danhSachGoi);
                
                danhSach.add(the);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public String getPrefixForCardType(String cardType) {
        String sql = "SELECT CardID FROM Card WHERE Type = ? ORDER BY CardID DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cardType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int lastCardID = rs.getInt("CardID");
                return String.valueOf(lastCardID).substring(0, String.valueOf(lastCardID).length() - 3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "1000"; // Mặc định prefix là 1000 nếu không tìm thấy
    }

    public ObservableList<GoiDangKy> getAllSubscriptions() {
        ObservableList<GoiDangKy> danhSach = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Subscription";
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
            e.printStackTrace();
        }
        return danhSach;
    }

    public int getTongSoGoiTap() {
        String sql = "SELECT COUNT(*) FROM Subscription";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public java.sql.Connection getConnection() {
        return this.conn;
    }

    public List<TheHoiVien> getMemberCards(int memberId) {
        List<TheHoiVien> cards = new ArrayList<>();
        String sql = "SELECT c.*, mc.PurchaseDate FROM Card c " +
                    "JOIN Member_Card mc ON c.CardID = mc.CardID " +
                    "WHERE mc.MemberID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheHoiVien card = new TheHoiVien();
                card.setCardID(rs.getInt("CardID"));
                card.setType(rs.getString("Type"));
                card.setPrice(rs.getDouble("Price"));
                card.setValidDuration(rs.getInt("ValidDuration"));
                card.setPurchaseDate(rs.getDate("PurchaseDate").toLocalDate());
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public ObservableList<GoiDangKy> getMemberSubscriptions(int memberId) {
        ObservableList<GoiDangKy> subscriptions = FXCollections.observableArrayList();
        String sql = "SELECT s.* FROM Subscription s " +
                     "JOIN Payment p ON s.SubscriptionID = p.SubscriptionID " +
                     "WHERE p.MemberID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GoiDangKy sub = new GoiDangKy();
                sub.setSubscriptionID(rs.getInt("SubscriptionID"));
                sub.setSubName(rs.getString("SubName"));
                sub.setType(rs.getString("Type"));
                sub.setStartDate(rs.getDate("StartDate").toLocalDate());
                sub.setSubscriptionDetail(rs.getString("SubscriptionDetail"));
                sub.setStatus(rs.getString("Status"));
                subscriptions.add(sub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    public List<Payment> getMemberPayments(int memberId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT p.*, s.SubName, c.Type as CardType FROM Payment p " +
                    "LEFT JOIN Subscription s ON p.SubscriptionID = s.SubscriptionID " +
                    "LEFT JOIN Card c ON p.CardID = c.CardID " +
                    "WHERE p.MemberID = ? " +
                    "ORDER BY p.PaymentDate DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setMemberID(rs.getInt("MemberID"));
                payment.setSubscriptionID(rs.getInt("SubscriptionID"));
                payment.setCardID(rs.getInt("CardID"));
                payment.setPaymentDate(rs.getDate("PaymentDate").toLocalDate());
                payment.setType(rs.getString("Type"));
                payment.setSubscriptionName(rs.getString("SubName"));
                payment.setCardType(rs.getString("CardType"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public boolean dangKyTheTap(int memberID, int cardID, String paymentType) {
        String querySubscription = "INSERT INTO Subscription (SubName, Type, StartDate, SubscriptionDetail, Status) VALUES (?, ?, ?, ?, ?)";
        String queryPayment = "INSERT INTO Payment (MemberID, SubscriptionID, CardID, PaymentDate, Type) VALUES (?, ?, ?, ?, ?)";
        String queryMemberCard = "INSERT INTO Member_Card (MemberID, CardID, PurchaseDate) VALUES (?, ?, ?)";
        
        try {
            conn.setAutoCommit(false);
            
            // 1. Lấy thông tin thẻ
            TheHoiVien card = layTheTheoMa(cardID);
            if (card == null) {
                throw new SQLException("Không tìm thấy thẻ");
            }
            
            // 2. Tạo gói đăng ký mới
            int subscriptionID = 0;
            try (PreparedStatement stmtSubscription = conn.prepareStatement(querySubscription, Statement.RETURN_GENERATED_KEYS)) {
                stmtSubscription.setString(1, card.getType() + " Package");
                stmtSubscription.setString(2, "Monthly");
                stmtSubscription.setDate(3, Date.valueOf(LocalDate.now()));
                stmtSubscription.setString(4, "Standard package for " + card.getType() + " card");
                stmtSubscription.setString(5, "Active");
                
                int affectedRows = stmtSubscription.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Không thể tạo gói đăng ký");
                }
                
                try (ResultSet generatedKeys = stmtSubscription.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subscriptionID = generatedKeys.getInt(1);
                        System.out.println("DEBUG: Generated SubscriptionID = " + subscriptionID);
                    } else {
                        throw new SQLException("Không thể lấy ID của gói đăng ký");
                    }
                }
            }
            
            if (subscriptionID == 0) {
                throw new SQLException("SubscriptionID không hợp lệ");
            }
            
            // 3. Thêm thanh toán
            try (PreparedStatement stmtPayment = conn.prepareStatement(queryPayment)) {
                stmtPayment.setInt(1, memberID);
                stmtPayment.setInt(2, subscriptionID);
                stmtPayment.setInt(3, cardID);
                stmtPayment.setDate(4, Date.valueOf(LocalDate.now()));
                stmtPayment.setString(5, paymentType);
                
                int affectedRows = stmtPayment.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Không thể thêm thanh toán");
                }
            }
            
            // 4. Thêm liên kết vào bảng Member_Card
            try (PreparedStatement stmtMemberCard = conn.prepareStatement(queryMemberCard)) {
                stmtMemberCard.setInt(1, memberID);
                stmtMemberCard.setInt(2, cardID);
                stmtMemberCard.setDate(3, Date.valueOf(LocalDate.now()));
                
                int affectedRows = stmtMemberCard.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Không thể thêm liên kết thẻ hội viên");
                }
            }
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, Object>> getThongTinDangKy(int memberID) {
        List<Map<String, Object>> result = new ArrayList<>();
        String query = "SELECT p.PaymentID, p.PaymentDate, p.Type as PaymentType, " +
                      "c.CardID, c.Type as CardType, c.Price, c.ValidDuration, " +
                      "m.MemberName, m.MemberPhoneNumber, m.Email " +
                      "FROM Payment p " +
                      "JOIN Card c ON p.CardID = c.CardID " +
                      "JOIN Member m ON p.MemberID = m.MemberID " +
                      "WHERE p.MemberID = ? " +
                      "ORDER BY p.PaymentDate DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("paymentID", rs.getInt("PaymentID"));
                row.put("paymentDate", rs.getDate("PaymentDate"));
                row.put("paymentType", rs.getString("PaymentType"));
                row.put("cardID", rs.getInt("CardID"));
                row.put("cardType", rs.getString("CardType"));
                row.put("price", rs.getDouble("Price"));
                row.put("validDuration", rs.getInt("ValidDuration"));
                row.put("memberName", rs.getString("MemberName"));
                row.put("phoneNumber", rs.getString("MemberPhoneNumber"));
                row.put("email", rs.getString("Email"));
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Map<String, Object>> getThongTinThanhToan(int memberID) {
        List<Map<String, Object>> result = new ArrayList<>();
        String query = "SELECT p.PaymentID, p.PaymentDate, p.Type as PaymentType, " +
                      "c.Type as CardType, c.Price, " +
                      "s.SubName, s.Type as SubscriptionType, s.Status " +
                      "FROM Payment p " +
                      "LEFT JOIN Card c ON p.CardID = c.CardID " +
                      "LEFT JOIN Subscription s ON p.SubscriptionID = s.SubscriptionID " +
                      "WHERE p.MemberID = ? " +
                      "ORDER BY p.PaymentDate DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("paymentID", rs.getInt("PaymentID"));
                row.put("paymentDate", rs.getDate("PaymentDate"));
                row.put("paymentType", rs.getString("PaymentType"));
                row.put("cardType", rs.getString("CardType"));
                row.put("price", rs.getDouble("Price"));
                row.put("subscriptionName", rs.getString("SubName"));
                row.put("subscriptionType", rs.getString("SubscriptionType"));
                row.put("status", rs.getString("Status"));
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lấy số lượng hội viên theo tháng trong 6 tháng gần nhất
    public Map<String, Integer> getSoHoiVienTheoThang() {
        Map<String, Integer> result = new HashMap<>();
        String query = "SELECT MONTH(PaymentDate) as month, COUNT(DISTINCT MemberID) as count " +
                      "FROM Payment " +
                      "WHERE PaymentDate >= DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) " +
                      "GROUP BY MONTH(PaymentDate) " +
                      "ORDER BY month";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int month = rs.getInt("month");
                int count = rs.getInt("count");
                result.put(String.valueOf(month), count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lấy thống kê số lượng thẻ theo loại đã được thanh toán
    public Map<String, Integer> getThongKeLoaiThe() {
        Map<String, Integer> result = new HashMap<>();
        String query = "SELECT c.Type, COUNT(*) as count " +
                      "FROM Payment p " +
                      "JOIN Card c ON p.CardID = c.CardID " +
                      "GROUP BY c.Type";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("Type");
                int count = rs.getInt("count");
                result.put(type, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
} 