package com.mycompany.project1.view;

import com.mycompany.project1.controller.HoiVienController;
import com.mycompany.project1.model.HoiVien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NhatKyTapLuyenView {
    private BorderPane root;
    private HoiVienController controller;
    private TableView<HoiVien> table;
    private DatePicker datePicker;
    private TextField tfMaThe;
    private TableView<HoiVien> historyTable;
    private Label dailyCount;
    private Label weeklyCount;
    private Label monthlyCount;

    public NhatKyTapLuyenView(BorderPane root) {
        this.root = root;
        this.controller = new HoiVienController();
    }

    public Node showNhatKyTapLuyen() {
        // Tạo VBox chính chứa tất cả nội dung
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));

        // Phần check-in
        VBox checkInSection = createCheckInSection();
        mainContent.getChildren().add(checkInSection);

        // Phần thống kê và lịch sử
        HBox bottomSection = new HBox(20);
        bottomSection.setPadding(new Insets(20, 0, 0, 0));

        // Thống kê
        VBox statsSection = createStatsSection();
        statsSection.setPrefWidth(300);

        // Lịch sử tập luyện
        VBox historySection = createHistorySection();
        historySection.setPrefWidth(600);

        bottomSection.getChildren().addAll(statsSection, historySection);
        mainContent.getChildren().add(bottomSection);

        // Wrap toàn bộ nội dung trong ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: white; -fx-border-color: white;");

        return scrollPane;
    }

    private VBox createCheckInSection() {
        VBox checkInBox = new VBox(10);
        checkInBox.setPadding(new Insets(20));
        checkInBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label title = new Label("Check-in/Check-out Hội Viên");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Tạo DatePicker để chọn ngày
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setPromptText("Chọn ngày");
        datePicker.setOnAction(e -> loadDataForDate(datePicker.getValue()));

        // Tạo TableView để hiển thị danh sách hội viên
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HoiVien, Integer> colMa = new TableColumn<>("Mã HV");
        colMa.setCellValueFactory(new PropertyValueFactory<>("maHoiVien"));

        TableColumn<HoiVien, String> colHoTen = new TableColumn<>("Họ Tên");
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<HoiVien, String> colThoiGian = new TableColumn<>("Thời gian check-in");
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGianCheckIn"));

        TableColumn<HoiVien, String> colTrangThai = new TableColumn<>("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

        // Thêm cột thao tác
        TableColumn<HoiVien, Void> colAction = new TableColumn<>("Thao tác");
        colAction.setCellFactory(col -> new TableCell<>() {
            private final Button btnCheckOut = new Button("Check-out");
            {
                btnCheckOut.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                btnCheckOut.setOnAction(e -> {
                    HoiVien hv = getTableView().getItems().get(getIndex());
                    handleCheckOut(hv);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HoiVien hv = getTableView().getItems().get(getIndex());
                    if ("Đang tập".equals(hv.getTrangThai())) {
                        setGraphic(btnCheckOut);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        table.getColumns().addAll(colMa, colHoTen, colThoiGian, colTrangThai, colAction);

        // Tạo form nhập thông tin check-in
        tfMaThe = new TextField();
        tfMaThe.setPromptText("Nhập mã hội viên");
        tfMaThe.setPrefWidth(200);
        
        Button btnCheckIn = new Button("Check-in");
        btnCheckIn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        
        // Xử lý sự kiện nhập mã thẻ
        tfMaThe.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                handleCheckIn(tfMaThe.getText());
            }
        });
        
        btnCheckIn.setOnAction(e -> handleCheckIn(tfMaThe.getText()));

        HBox checkInForm = new HBox(10, tfMaThe, btnCheckIn);
        checkInForm.setAlignment(Pos.CENTER);

        // Thêm các thành phần vào container
        checkInBox.getChildren().addAll(
            title,
            new HBox(10, new Label("Chọn ngày:"), datePicker),
            checkInForm,
            table
        );

        // Load dữ liệu cho ngày hiện tại
        loadDataForDate(LocalDate.now());

        return checkInBox;
    }

    private void handleCheckIn(String maHV) {
        if (maHV.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập mã hội viên!");
            alert.showAndWait();
            return;
        }

        try {
            int maHoiVien = Integer.parseInt(maHV);
            if (controller.checkInHoiVien(maHoiVien)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Check-in thành công!");
                alert.showAndWait();
                tfMaThe.clear();
                loadDataForDate(datePicker.getValue());
                
                // Cập nhật lại bảng lịch sử tập luyện
                LocalDate today = LocalDate.now();
                historyTable.setItems(controller.getLichSuTapLuyen(today.minusDays(7), today));
                
                // Cập nhật thống kê
                updateStatistics();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Check-in thất bại! Hội viên đã check-in hôm nay hoặc không tồn tại.");
                alert.showAndWait();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Mã hội viên không hợp lệ!");
            alert.showAndWait();
        }
    }

    private void handleCheckOut(HoiVien hoiVien) {
        if (controller.checkOutHoiVien(hoiVien.getMaHoiVien())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Check-out thành công!");
            alert.showAndWait();
            loadDataForDate(datePicker.getValue());
            
            // Cập nhật lại bảng lịch sử tập luyện
            LocalDate today = LocalDate.now();
            historyTable.setItems(controller.getLichSuTapLuyen(today.minusDays(7), today));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Check-out thất bại!");
            alert.showAndWait();
        }
    }

    private void updateStatistics() {
        LocalDate today = LocalDate.now();
        int soHoiVienHienTai = controller.getSoHoiVienTapTheoNgay(today);
        int soHoiVienTuan = controller.getSoHoiVienTapTheoTuan(today.minusDays(6));
        int soHoiVienThang = controller.getSoHoiVienTapTheoThang(today.minusDays(29));

        // Cập nhật các label thống kê
        dailyCount.setText(soHoiVienHienTai + " hội viên");
        weeklyCount.setText(soHoiVienTuan + " hội viên");
        monthlyCount.setText(soHoiVienThang + " hội viên");
    }

    private void loadDataForDate(LocalDate date) {
        if (date != null) {
            table.setItems(controller.getDanhSachHoiVienCheckIn(date));
        }
    }

    private VBox createStatsSection() {
        VBox statsBox = new VBox(10);
        statsBox.setPadding(new Insets(20));
        statsBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label title = new Label("Thống kê tập luyện");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Thống kê theo ngày
        VBox dailyStats = new VBox(5);
        dailyStats.setPadding(new Insets(10));
        dailyStats.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 5;");
        
        Label dailyTitle = new Label("Hôm nay:");
        dailyCount = new Label("0 hội viên");
        dailyStats.getChildren().addAll(dailyTitle, dailyCount);

        // Thống kê theo tuần
        VBox weeklyStats = new VBox(5);
        weeklyStats.setPadding(new Insets(10));
        weeklyStats.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 5;");
        
        Label weeklyTitle = new Label("Tuần này:");
        weeklyCount = new Label("0 hội viên");
        weeklyStats.getChildren().addAll(weeklyTitle, weeklyCount);

        // Thống kê theo tháng
        VBox monthlyStats = new VBox(5);
        monthlyStats.setPadding(new Insets(10));
        monthlyStats.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 5;");
        
        Label monthlyTitle = new Label("Tháng này:");
        monthlyCount = new Label("0 hội viên");
        monthlyStats.getChildren().addAll(monthlyTitle, monthlyCount);

        statsBox.getChildren().addAll(title, dailyStats, weeklyStats, monthlyStats);
        return statsBox;
    }

    private VBox createHistorySection() {
        VBox historyBox = new VBox(10);
        historyBox.setPadding(new Insets(20));
        historyBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label title = new Label("Lịch sử tập luyện");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Tạo bảng lịch sử
        historyTable = new TableView<>();
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HoiVien, Integer> colMa = new TableColumn<>("Mã HV");
        colMa.setCellValueFactory(new PropertyValueFactory<>("maHoiVien"));

        TableColumn<HoiVien, String> colHoTen = new TableColumn<>("Họ Tên");
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<HoiVien, String> colNgay = new TableColumn<>("Ngày tập");
        colNgay.setCellValueFactory(new PropertyValueFactory<>("ngayTap"));

        TableColumn<HoiVien, String> colThoiGian = new TableColumn<>("Thời gian");
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGianCheckIn"));

        historyTable.getColumns().addAll(colMa, colHoTen, colNgay, colThoiGian);

        // Thêm DatePicker để lọc theo ngày
        HBox filterBox = new HBox(10);
        filterBox.setAlignment(Pos.CENTER_LEFT);
        
        DatePicker startDate = new DatePicker(LocalDate.now().minusDays(7));
        DatePicker endDate = new DatePicker(LocalDate.now());
        
        Button filterBtn = new Button("Lọc");
        filterBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        
        filterBox.getChildren().addAll(
            new Label("Từ ngày:"), startDate,
            new Label("Đến ngày:"), endDate,
            filterBtn
        );

        filterBtn.setOnAction(e -> {
            if (startDate.getValue() != null && endDate.getValue() != null) {
                loadHistoryData(startDate.getValue(), endDate.getValue());
            }
        });

        historyBox.getChildren().addAll(title, filterBox, historyTable);
        return historyBox;
    }

    private void loadHistoryData(LocalDate startDate, LocalDate endDate) {
        // TODO: Load dữ liệu lịch sử từ controller
        // historyTable.setItems(controller.getLichSuTapLuyen(startDate, endDate));
    }
} 