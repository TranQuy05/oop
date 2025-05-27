package com.mycompany.project1.view;

import com.mycompany.project1.DAO.TheHoiVienDAO;
import com.mycompany.project1.DAO.PaymentDAO;
import com.mycompany.project1.model.TheHoiVien;
import com.mycompany.project1.model.GoiDangKy;
import com.mycompany.project1.model.Payment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class QuanLyTheView {
    private BorderPane root;
    private TheHoiVienDAO theHoiVienDAO;
    private TableView<GoiDangKy> tableGoi;
    private TextField tfMaThe;
    private TextField tfGiaThe;
    private TextField tfThoiHan;
    private ComboBox<String> cbLoaiThe;
    private ComboBox<String> cbGoiDangKy;
    private DatePicker dpNgayBatDau;
    private TextField tfMaHoiVien;
    private TextField tfMaGoi;

    public QuanLyTheView(BorderPane root) {
        this.root = root;
        this.theHoiVienDAO = new TheHoiVienDAO();
    }
        private void showAlert(String title, String content, Alert.AlertType type) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
    }
    public Node showQuanLyThe() {
        VBox container = new VBox(20);
        container.setPadding(new Insets(20));

        // Tạo form đăng ký thẻ và gói tập
        VBox formBox = new VBox(15);
        formBox.setPadding(new Insets(20));
        formBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-radius: 5;");

        Label lblTitle = new Label("Đăng Ký Thẻ Và Gói Tập");
        lblTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Form thông tin thẻ
        GridPane cardForm = new GridPane();
        cardForm.setHgap(10);
        cardForm.setVgap(10);
        cardForm.setPadding(new Insets(10));

        cbLoaiThe = new ComboBox<>();
        cbLoaiThe.setItems(theHoiVienDAO.getCardTypes());
        cbLoaiThe.setPromptText("Chọn loại thẻ");
        cbLoaiThe.setPrefWidth(200);

        tfMaThe = new TextField();
        tfMaThe.setEditable(false);
        tfMaThe.setPromptText("Mã thẻ");

        tfGiaThe = new TextField();
        tfGiaThe.setEditable(false);
        tfGiaThe.setPromptText("Giá thẻ");

        tfThoiHan = new TextField();
        tfThoiHan.setEditable(false);
        tfThoiHan.setPromptText("Thời hạn (tháng)");

        cardForm.add(new Label("Loại thẻ:"), 0, 0);
        cardForm.add(cbLoaiThe, 1, 0);
        cardForm.add(new Label("Mã thẻ:"), 0, 1);
        cardForm.add(tfMaThe, 1, 1);
        cardForm.add(new Label("Giá thẻ:"), 0, 2);
        cardForm.add(tfGiaThe, 1, 2);
        cardForm.add(new Label("Thời hạn:"), 0, 3);
        cardForm.add(tfThoiHan, 1, 3);

        // Form thông tin gói đăng ký
        GridPane subscriptionForm = new GridPane();
        subscriptionForm.setHgap(10);
        subscriptionForm.setVgap(10);
        subscriptionForm.setPadding(new Insets(10));

        tfMaHoiVien = new TextField();
        tfMaHoiVien.setPromptText("Mã hội viên");

        tfMaGoi = new TextField();
        tfMaGoi.setPromptText("Nhập mã gói tập");

        subscriptionForm.add(new Label("Mã hội viên:"), 0, 0);
        subscriptionForm.add(tfMaHoiVien, 1, 0);
        subscriptionForm.add(new Label("Mã gói tập:"), 0, 1);
        subscriptionForm.add(tfMaGoi, 1, 1);

        // Nút đăng ký
        Button btnDangKy = new Button("Đăng Ký");
        btnDangKy.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        btnDangKy.setPrefWidth(200);

        formBox.getChildren().addAll(lblTitle, cardForm, new Separator(), subscriptionForm, btnDangKy);
        formBox.setAlignment(Pos.CENTER);

        // Bảng hiển thị gói đăng ký
        VBox tableBox = new VBox(10);
        Label lblTableTitle = new Label("Danh Sách Gói Đăng Ký");
        lblTableTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        tableGoi = new TableView<>();
        tableGoi.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<GoiDangKy, Integer> colMaGoi = new TableColumn<>("Mã gói");
        colMaGoi.setCellValueFactory(new PropertyValueFactory<>("subscriptionID"));

        TableColumn<GoiDangKy, String> colTenGoi = new TableColumn<>("Tên gói");
        colTenGoi.setCellValueFactory(new PropertyValueFactory<>("subName"));

        TableColumn<GoiDangKy, String> colLoaiGoi = new TableColumn<>("Loại gói");
        colLoaiGoi.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<GoiDangKy, String> colChiTiet = new TableColumn<>("Chi tiết");
        colChiTiet.setCellValueFactory(new PropertyValueFactory<>("subscriptionDetail"));

        TableColumn<GoiDangKy, String> colTrangThai = new TableColumn<>("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableGoi.getColumns().addAll(colMaGoi, colTenGoi, colLoaiGoi, colChiTiet, colTrangThai);

        // Hiển thị danh sách gói đăng ký
        tableGoi.setItems(theHoiVienDAO.getAllSubscriptions());

        tableBox.getChildren().addAll(lblTableTitle, tableGoi);

        // Xử lý sự kiện
        cbLoaiThe.setOnAction(e -> {
            String selectedType = cbLoaiThe.getValue();
            if (selectedType != null) {
                // Lấy thông tin thẻ từ database dựa trên loại thẻ
                TheHoiVien the = theHoiVienDAO.layTheTheoLoai(selectedType);
                if (the != null) {
                    // Hiển thị thông tin thẻ
                    tfMaThe.setText(String.valueOf(the.getCardID()));
                    tfGiaThe.setText(String.valueOf(the.getPrice()));
                    tfThoiHan.setText(String.valueOf(the.getValidDuration()));
                } else {
                    showAlert("Lỗi", "Không tìm thấy thông tin thẻ!", Alert.AlertType.ERROR);
                }
            }
        });

        btnDangKy.setOnAction(e -> {
            try {
                // Kiểm tra dữ liệu đầu vào
                if (cbLoaiThe.getValue() == null || tfMaHoiVien.getText().trim().isEmpty() || 
                    tfMaGoi.getText().trim().isEmpty()) {
                    showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin!", Alert.AlertType.ERROR);
                    return;
                }

                // Lấy thông tin thẻ từ loại thẻ đã chọn
                TheHoiVien the = theHoiVienDAO.layTheTheoLoai(cbLoaiThe.getValue());
                if (the == null) {
                    showAlert("Lỗi", "Không tìm thấy thông tin thẻ!", Alert.AlertType.ERROR);
                    return;
                }

                // Tạo gói đăng ký mới
                GoiDangKy goi = new GoiDangKy();
                goi.setSubscriptionID(Integer.parseInt(tfMaGoi.getText()));
                goi.setStartDate(LocalDate.now());
                goi.setStatus("Active");
                
                // Lấy thông tin gói từ database
                ObservableList<GoiDangKy> allSubscriptions = theHoiVienDAO.getAllSubscriptions();
                GoiDangKy selectedSubscription = allSubscriptions.stream()
                    .filter(s -> s.getSubscriptionID() == Integer.parseInt(tfMaGoi.getText()))
                    .findFirst()
                    .orElse(null);
                
                if (selectedSubscription == null) {
                    showAlert("Lỗi", "Không tìm thấy gói tập với mã này!", Alert.AlertType.ERROR);
                    return;
                }
                
                // Copy thông tin từ gói đã chọn
                goi.setSubName(selectedSubscription.getSubName());
                goi.setType(selectedSubscription.getType());
                goi.setSubscriptionDetail(selectedSubscription.getSubscriptionDetail());

                // Thêm vào database
                int memberID = Integer.parseInt(tfMaHoiVien.getText());
                if (theHoiVienDAO.themThe(the, memberID) && theHoiVienDAO.themGoiDangKy(the.getCardID(), goi)) {
                    // Tạo lịch sử thanh toán
                    Payment payment = new Payment();
                    payment.setMemberID(memberID);
                    payment.setCardID(the.getCardID());
                    payment.setPaymentDate(LocalDate.now());
                    payment.setType("Thanh toán thẻ " + the.getType());
                    payment.setSubscriptionID(goi.getSubscriptionID());
                    
                    PaymentDAO paymentDAO = new PaymentDAO();
                    if (paymentDAO.themPayment(payment)) {
                        showAlert("Thành công", "Đăng ký thẻ và gói tập thành công!", Alert.AlertType.INFORMATION);
                        // Cập nhật bảng gói đăng ký
                        tableGoi.getItems().clear();
                        tableGoi.setItems(theHoiVienDAO.getAllSubscriptions());
                        // Reset form
                        resetForm();

                        // GỌI THÊM DÒNG NÀY:
                        showMemberSubscriptions(memberID);
                    }
                } else {
                    showAlert("Lỗi", "Không thể đăng ký thẻ và gói tập!", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                showAlert("Lỗi", "Vui lòng nhập đúng định dạng số!", Alert.AlertType.ERROR);
            }
        });

        container.getChildren().addAll(formBox, tableBox);
        return container;
    }

    private void resetForm() {
        cbLoaiThe.setValue(null);
        tfMaThe.clear();
        tfGiaThe.clear();
        tfThoiHan.clear();
        tfMaHoiVien.clear();
        tfMaGoi.clear();
    }



    private void handleDangKy() {
        try {
            // Kiểm tra thông tin nhập vào
            if (cbLoaiThe.getValue() == null) {
                showAlert("Lỗi", "Vui lòng chọn loại thẻ!", Alert.AlertType.ERROR);
                return;
            }
            
            if (tfMaHoiVien.getText().isEmpty()) {
                showAlert("Lỗi", "Vui lòng nhập mã hội viên!", Alert.AlertType.ERROR);
                return;
            }
            
            // Lấy thông tin thẻ từ loại thẻ đã chọn
            TheHoiVien the = theHoiVienDAO.layTheTheoLoai(cbLoaiThe.getValue());
            if (the == null) {
                showAlert("Lỗi", "Không tìm thấy thông tin thẻ!", Alert.AlertType.ERROR);
                return;
            }
            
            // Thêm thẻ mới cho hội viên
            int memberID = Integer.parseInt(tfMaHoiVien.getText());
            if (theHoiVienDAO.themThe(the, memberID)) {
                showAlert("Thành công", "Đăng ký thẻ thành công!", Alert.AlertType.INFORMATION);
                // Cập nhật hiển thị
                tfMaThe.setText(String.valueOf(the.getCardID()));
                tfGiaThe.setText(String.valueOf(the.getPrice()));
                tfThoiHan.setText(String.valueOf(the.getValidDuration()));
            } else {
                showAlert("Lỗi", "Đăng ký thẻ thất bại!", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Vui lòng nhập đúng định dạng số!", Alert.AlertType.ERROR);
        }
    }
// sau khi đăng ký thành công hiển thị danh sách gói tập của hội viên đã đăng ký
    private void showMemberSubscriptions(int memberID) {
        // Lấy danh sách gói tập của hội viên từ DAO
        ObservableList<GoiDangKy> subscriptions = theHoiVienDAO.getMemberSubscriptions(memberID);

        // Tạo nội dung hiển thị
        StringBuilder sb = new StringBuilder();
        sb.append("Mã hội viên: ").append(memberID).append("\n");
        sb.append("Các gói tập đã đăng ký:\n");
        for (GoiDangKy goi : subscriptions) {
            sb.append("- ").append(goi.getSubName())
              .append(" (").append(goi.getType()).append(")\n");
        }

        // Hiển thị Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông tin hội viên");
        alert.setHeaderText("Đăng ký thành công!");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }
} 