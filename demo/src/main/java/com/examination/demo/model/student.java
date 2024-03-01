package com.examination.demo.model;


public class Student {
    String studentName;
    String saltPassword;
    String email;
    Integer isActive;
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
