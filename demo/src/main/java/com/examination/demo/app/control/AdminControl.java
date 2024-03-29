package com.examination.demo.app.control;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
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
import com.examination.demo.model.Student;
import com.examination.demo.service.AdminService;
import com.examination.demo.service.StudentService;



@Controller
public class AdminControl {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/index")
    @CrossOrigin
    @ResponseBody
    public String index() {
        String a = "Hello world";
        return a;
    }

    @PostMapping("/authentication")
    @CrossOrigin
    @ResponseBody
    public String login(@RequestBody String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = null;
        Result<Admin> result = new Result<>();
        
        admin = mapper.readValue(json, Admin.class);
        
        Student studentTemp = studentService.login(admin.getAdminName(), admin.getSaltPassword());
        
    
        if (studentTemp != null) {
            result.setCode(Constant.CODE_200);
            result.setMessage("student");
            return mapper.writeValueAsString(result);
        }

        Admin adminTemp = adminService.login(admin.getAdminName(), admin.getSaltPassword());
        
        if(adminTemp == null){
            result.setCode(Constant.CODE_401);
            result.setMessage("用户名或密码错误");
            return mapper.writeValueAsString(result);
        }

        adminTemp.setSaltPassword("");
        
        
        result.setCode(Constant.CODE_200);
        result.setMessage("admin");
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
        adminService.deleteStudent(adminName);

        Result<String> result = new Result<>();
        result.setCode("200");
        result.setMessage("删除成功");

        return result.toString();

    }


   


    
}
