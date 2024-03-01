package com.examination.demo.model;


public class student {
    String userName;
    String saltPassword;
    String email;
    Integer isActive;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSaltPassword() {
        return saltPassword;
    }
    public void setSaltPassword(String saltPassword) {
        this.saltPassword = saltPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
