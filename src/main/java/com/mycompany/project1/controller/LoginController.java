package com.mycompany.project1.controller;

public class LoginController {
    public boolean authenticate(String username, String password) {
        // Tạm thời hard-code để kiểm tra
        return username.equals("admin") && password.equals("123");
    }
}
 