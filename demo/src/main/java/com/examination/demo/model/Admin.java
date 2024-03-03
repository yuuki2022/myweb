package com.examination.demo.model;

import lombok.Data;

@Data
public class Admin {
    private String adminName;
   
    private String saltPassword;
   
    private String role;
    
    
}
