package com.examination.demo.app.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.model.Result;
import com.examination.demo.model.Student;
import com.examination.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class StudentControl {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    @ResponseBody
    public String getStudents() {
        List<Student> students = studentService.getAllStudent();
        ///////////将students转为json格式返回
        ObjectMapper mapper = new ObjectMapper();
        Result<String> responseBody = new Result<>();

        try {
            String studentsJson = mapper.writeValueAsString(students);
            responseBody.setCode("200");
            responseBody.setMessage("success");
            responseBody.setData(studentsJson);
            return mapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseBody.setCode("500");
        responseBody.setMessage("error");
        return responseBody.toString();
    
    }


    @GetMapping("/student")
    @ResponseBody
    public String getMethodName(@RequestParam String studentId) {
        System.out.println(studentId);
        Student student = studentService.getStudentById(studentId);
        ObjectMapper mapper = new ObjectMapper();
        Result<Student> responseBody = new Result<>();
        try {
            responseBody.setCode("200");
            responseBody.setMessage("找到学生");
            responseBody.setData(student);
            return mapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

    responseBody.setCode("500");
    responseBody.setMessage("error");
    return responseBody.toString();
}
    
}
