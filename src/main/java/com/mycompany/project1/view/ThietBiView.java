package com.mycompany.project1.view;

import com.mycompany.project1.model.ThietBi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class ThietBiView {
    private BorderPane root;
        public ThietBiView(BorderPane root) {
        this.root = root;
    }
    public void showThietBi() {
        
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label title = new Label("Danh sách thiết bị");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TableView<ThietBi> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ThietBi, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<ThietBi, String> colTen = new TableColumn<>("Tên thiết bị");
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));

        TableColumn<ThietBi, String> colLoai = new TableColumn<>("Loại");
        colLoai.setCellValueFactory(new PropertyValueFactory<>("loaiThietBi"));

        TableColumn<ThietBi, String> colTrangThai = new TableColumn<>("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(colID, colTen, colLoai, colTrangThai);

        layout.getChildren().addAll(title, table);
        root.setCenter(layout);
    }

 }
