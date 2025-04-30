/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import com.mycompany.project1.model.KhoaHoc;
import com.mycompany.project1.model.LopHoc;
import java.time.LocalDate;
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
public class LopHocView {
    private BorderPane root;

    public LopHocView(BorderPane root) {
        this.root = root;
    }

    public void showLopHoc() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));


        TableView<LopHoc> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<LopHoc, Integer> colID = new TableColumn<>("Mã Lớp");
        colID.setCellValueFactory(new PropertyValueFactory<>("classID"));

        TableColumn<LopHoc, String> colName = new TableColumn<>("Lớp Học");
        colName.setCellValueFactory(new PropertyValueFactory<>("className"));

        TableColumn<LopHoc, String> colTrainer = new TableColumn<>("Huấn luyện viên");
        colTrainer.setCellValueFactory(new PropertyValueFactory<>("trainerID"));

        TableColumn<LopHoc, LocalDate> colSchedule = new TableColumn<>("Lịch học");
        colSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));

        table.getColumns().addAll(colID, colName, colTrainer, colSchedule);


        TextField tfId = new TextField(); tfId.setPromptText("Mã Lớp");
        TextField tfName = new TextField(); tfName.setPromptText("Tên Lớp");
        TextField tfTrainerId = new TextField(); tfTrainerId.setPromptText("Mã HLV");
        TextField tfSchedule = new TextField(); tfSchedule.setPromptText("Lịch học (yyyy-mm-dd)");

        HBox form = new HBox(10, tfId, tfName, tfTrainerId, tfSchedule);
        form.setAlignment(Pos.CENTER);
        VBox formSection = new VBox(10, new Label("Nhập thông tin lớp:"), form);

     
        TextField tfSearchId = new TextField(); tfSearchId.setPromptText("Nhập mã lớp cần tìm");
        HBox searchBox = new HBox(10, tfSearchId);
        searchBox.setAlignment(Pos.CENTER);
        VBox searchSection = new VBox(10, new Label("Tìm kiếm lớp:"), searchBox);

        Button btnThem = new Button(" Thêm lớp");
        btnThem.setOnAction(e -> {
            if (container.getChildren().contains(formSection)) {
                container.getChildren().remove(formSection);
                btnThem.setText("Thêm lớp");
            } else {
                container.getChildren().add(formSection);
                btnThem.setText(" Ẩn");
            }
        });

    
        Button btnTimKiem = new Button(" Tìm kiếm");
        btnTimKiem.setOnAction(e -> {
            if (container.getChildren().contains(searchSection)) {
                container.getChildren().remove(searchSection);
                btnTimKiem.setText(" Tìm kiếm");
            } else {
                container.getChildren().add(searchSection);
                btnTimKiem.setText(" Ẩn");
            }
        });


       // Giao diện chính
       container.getChildren().addAll(
           new Label("Danh sách lớp học: "),
           table,
           btnThem,
           btnTimKiem
       );

        root.setCenter(container);
    }
    }
