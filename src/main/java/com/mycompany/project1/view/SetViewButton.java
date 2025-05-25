/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project1.view;

import javafx.scene.control.Button;

/**
 *
 * @author dtquy
 */
public class SetViewButton {
    private Button createMenuButton(String text) {
    Button btn = new Button(text);
    btn.setMaxWidth(Double.MAX_VALUE);
    btn.setStyle(
        "-fx-background-color: transparent;" +  // Không nền
        "-fx-text-fill: white;" +               // Màu chữ trắng
        "-fx-border-width: 0;" +                // Không viền
        "-fx-font-size: 14px;" +
        "-fx-cursor: hand;"                     // Trỏ chuột dạng tay
    );

    // Khi di chuột vào -> sáng lên
    btn.setOnMouseEntered(e -> btn.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-text-fill: #00ffcc;" +            // Màu chữ sáng hơn
        "-fx-border-width: 0;" +
        "-fx-font-size: 14px;" +
        "-fx-cursor: hand;"
    ));

    // Khi di chuột ra -> trở về bình thường
    btn.setOnMouseExited(e -> btn.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-text-fill: white;" +
        "-fx-border-width: 0;" +
        "-fx-font-size: 14px;" +
        "-fx-cursor: hand;"
    ));

    return btn;
}

}
