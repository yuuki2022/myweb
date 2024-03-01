package com.examination.demo.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("`admin`")
public class AdminDO {
    @TableId(value = "admin_name")
    private String adminName;

    @TableField("salt_password")
    private String saltPassword;

    @TableField("role")
    private String role;
    
    public AdminDO(String adminName, String saltPassword, String role) {
        this.adminName = adminName;
        this.saltPassword = saltPassword;
        this.role = role;
    }
    
}
