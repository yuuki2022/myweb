package com.examination.demo.dataobject;



import java.util.List;

import lombok.Data;

@Data
public class AdminDO {
    private String adminName;

    private String saltPassword;

    private String role;
    
    
    public AdminDO(String adminName, String saltPassword, String role) {
        this.adminName = adminName;
        this.saltPassword = saltPassword;
        this.role = role;
    }
    
}
