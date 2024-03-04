package com.examination.demo.app.control;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.examination.demo.common.Constant;
import com.examination.demo.model.Admin;
import com.examination.demo.model.Result;
import com.examination.demo.service.AdminService;



@Controller
public class AdminControl {

    @Autowired
    AdminService adminService;

    @GetMapping("/index")
    @CrossOrigin
    @ResponseBody
    public String index() {
        String a = "Hello world";
        return a;
    }

    @PostMapping("/admin/authentication")
    @CrossOrigin
    @ResponseBody
    public String postMethodName(@RequestBody String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = null;
        Result<Admin> result = new Result<>();
        
        admin = mapper.readValue(json, Admin.class);
        
        Admin adminTemp = adminService.login(admin.getAdminName(), admin.getSaltPassword());
        
        if(adminTemp == null){
            result.setCode(Constant.CODE_401);
            result.setMessage("用户名或密码错误");

            return mapper.writeValueAsString(result);
        }

        adminTemp.setSaltPassword("");
        
        
        result.setCode(Constant.CODE_200);
        result.setMessage("登录成功");
        result.setData(adminTemp);
        System.out.println(result.toString());
        return mapper.writeValueAsString(result);
    }

    @PostMapping("/admin/register")
    @CrossOrigin
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

    @Delete("/admin/delete")
    @ResponseBody
    public String deleteAdmin(@RequestParam String adminName){
        
    }


    
}
