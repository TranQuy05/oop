/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.model.HoiVien;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author dtquy
 */
public class HoiVienView {
    private BorderPane root;

    public HoiVienView (BorderPane root) {
        this.root = root;
    }

public void showHoiVien() {
    VBox container = new VBox(10);
    container.setPadding(new Insets(20));

    TableView<HoiVien> table = new TableView<>();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<HoiVien, Integer> colMa = new TableColumn<>("Mã HV");
    TableColumn<HoiVien, String> colTen = new TableColumn<>("Họ tên");
    TableColumn<HoiVien, Integer> colNgaySinh = new TableColumn<>("Ngày sinh");
    TableColumn<HoiVien, Integer> colSdt = new TableColumn<>("SĐT");
    TableColumn<HoiVien, String> colGioiTinh = new TableColumn<>("Giới tính");
    TableColumn<HoiVien, String> colDiaChi = new TableColumn<>("Địa chỉ");
    TableColumn<HoiVien, String> colEmail = new TableColumn<>("Email");

    colMa.setCellValueFactory(new PropertyValueFactory<>("maHoiVien"));
    colTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
    colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
    colSdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
    colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
    colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    table.getColumns().addAll(colMa, colTen, colNgaySinh, colSdt, colGioiTinh, colDiaChi, colEmail);

    // Tạo form nhập thông tin hội viên
    TextField tfMa = new TextField(); tfMa.setPromptText("Mã hội viên");
    TextField tfTen = new TextField(); tfTen.setPromptText("Họ tên");
    TextField tfNgaySinh = new TextField(); tfNgaySinh.setPromptText("YYYY-MM-DD");
    TextField tfSdt = new TextField(); tfSdt.setPromptText("SĐT");
    TextField tfGioiTinh = new TextField(); tfGioiTinh.setPromptText("Giới tính");
    TextField tfDiaChi = new TextField(); tfDiaChi.setPromptText("Địa chỉ");
    TextField tfEmail = new TextField(); tfEmail.setPromptText("Email");

    HBox form = new HBox(10, tfTen, tfNgaySinh, tfSdt, tfGioiTinh, tfDiaChi, tfEmail);
    form.setAlignment(Pos.CENTER);
    HBox search = new HBox(10,tfMa);
    


    VBox formSection = new VBox(10, new Label("Nhập thông tin hội viên:"), form);

    // Nút để toggle hiển thị form
    Button btnThem = new Button("Thêm hội viên");
    btnThem.setOnAction(e -> {
        if (container.getChildren().contains(formSection)) {
            container.getChildren().remove(formSection);
            btnThem.setText("➕ Thêm hội viên");
        } else {
            container.getChildren().add(formSection);
            btnThem.setText("✖ Ẩn form");
        }
    });
    
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Tìm theo mã hội viên...");
        tfSearch.setMaxWidth(200);
        Button btnTim = new Button("Tìm kiếm");
    HBox thanhTimKiem = new HBox(10, tfSearch, btnTim);
    
    // Giao diện chính
    container.getChildren().addAll(
        new Label("Danh sách hội viên:"),
        thanhTimKiem,
        table,
        btnThem
        
    );

    root.setCenter(container);
}}



