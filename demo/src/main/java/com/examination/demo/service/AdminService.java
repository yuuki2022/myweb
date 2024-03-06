package com.examination.demo.service;

import com.examination.demo.model.Admin;

public interface AdminService {
  
    public Admin login(String adminName, String saltPassword);
    
    public void register(String adminName, String saltPassword, String role);
    
    public Admin selectByAdminName(String adminName);
    
    public void updatePassword(String adminName, String saltPassword);

    public void deleteStudent(String adminName);
}
