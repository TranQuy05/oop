package com.mycompany.project1.view;

import com.mycompany.project1.model.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class NhanVienView {
    private BorderPane root;

    public NhanVienView(BorderPane root) {
        this.root = root;
    }

    public void showEmployeePage() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        TableView<NhanVien> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<NhanVien, Integer> colMa = new TableColumn<>("Mã NV");
        colMa.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));

        TableColumn<NhanVien, String> colHoTen = new TableColumn<>("Họ Tên");
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<NhanVien, String> colChucVu = new TableColumn<>("Chức vụ");
        colChucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));

        TableColumn<NhanVien, Integer> colNgaySinh = new TableColumn<>("Ngày sinh");
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));

        TableColumn<NhanVien, String> colGioiTinh = new TableColumn<>("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));

        TableColumn<NhanVien, String> colSDT = new TableColumn<>("SĐT");
        colSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));

        TableColumn<NhanVien, String> colDiaChi = new TableColumn<>("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

        TableColumn<NhanVien, LocalDate> colNgayVaoLam = new TableColumn<>("Ngày vào làm");
        colNgayVaoLam.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));

        TableColumn<NhanVien, Double> colSoCong = new TableColumn<>("Số công");
        colSoCong.setCellValueFactory(new PropertyValueFactory<>("soCong"));

        table.getColumns().addAll(colMa, colHoTen, colChucVu, colNgaySinh, colGioiTinh,
                                  colSDT, colDiaChi, colNgayVaoLam, colSoCong);

        container.getChildren().add(table);
        root.setCenter(container);
    }

   
}
