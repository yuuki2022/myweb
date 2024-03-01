package com.examination.demo.app.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.examination.demo.dataobject.AdminDO;
import com.examination.demo.model.Admin;
import com.examination.demo.model.Result;
import com.examination.demo.service.AdminService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminControl {

    @Autowired
    AdminService adminService;

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        String a = "Hello world";
        return a;
    }

    @PostMapping("/authentication")
    @ResponseBody
    public String postMethodName(@RequestBody String json) throws Exception{
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = null;
        
        admin = mapper.readValue(json, Admin.class);
        
        Admin adminTemp = adminService.login(admin.getAdminName(), admin.getSaltPassword());
        if(adminTemp == null){
            return "用户名或密码错误";
        }
        
        Result<Admin> result = new Result<>();
        result.setCode("200");
        result.setMessage("登录成功");
        result.setData(adminTemp);
        return mapper.writeValueAsString(result);
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody String json) throws Exception{
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = null;
        
        admin = mapper.readValue(json, Admin.class);
        
        System.out.println(admin.getAdminName());
        Admin adminTemp = adminService.selectByAdminName(admin.getAdminName());
        if(adminTemp != null){
            return "你的用户名已经被使用了，请换一个用户名";
        }

        adminService.register(admin.getAdminName(), admin.getSaltPassword(), "admin");

        Result<Admin> result = new Result<>();
        result.setCode("200");
        result.setMessage("注册成功");
        result.setData(admin);
        return mapper.writeValueAsString(result);
    }

    @GetMapping("/getAdmins")
    @ResponseBody
    public String getMethodName() {
        adminService.getAllAdmins();
        return "getAdmins";
    }
    
}
