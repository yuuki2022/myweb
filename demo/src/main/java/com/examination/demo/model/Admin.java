package com.examination.demo.model;

import lombok.Data;

@Data
public class Admin {
    private String adminName;
    private String saltPassword;
    private String role;

    public Admin(String adminName, String saltPassword, String role) {
        this.adminName = adminName;
        this.saltPassword = saltPassword;
        this.role = role;
    }
    
    
}
