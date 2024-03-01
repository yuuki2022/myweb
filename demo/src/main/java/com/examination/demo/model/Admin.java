package com.examination.demo.model;


public class Admin {
    private String adminName;
    private String saltPassword;
    private String role;
    
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getSaltPassword() {
        return saltPassword;
    }
    public void setSaltPassword(String saltPassword) {
        this.saltPassword = saltPassword;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
   
    
}
