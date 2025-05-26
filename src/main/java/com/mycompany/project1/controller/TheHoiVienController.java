package com.mycompany.project1.controller;

import com.mycompany.project1.DAO.TheHoiVienDAO;

public class TheHoiVienController {
    private TheHoiVienDAO theHoiVienDAO;
    
    public TheHoiVienController() {
        this.theHoiVienDAO = new TheHoiVienDAO();
    }
    
    public int getTongSoGoiTap() {
        return theHoiVienDAO.getTongSoGoiTap();
    }
} 