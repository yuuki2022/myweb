package com.examination.demo.app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.common.Constant;
import com.examination.demo.model.Admin;
import com.examination.demo.model.Course;
import com.examination.demo.model.Paging;
import com.examination.demo.model.Paper;
import com.examination.demo.model.Result;
import com.examination.demo.model.Student;
import com.examination.demo.service.AdminService;
import com.examination.demo.service.PaperService;
import com.examination.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentControl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    @CrossOrigin
    @ResponseBody
    public String getStudents(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Student> students = studentService.getAllStudent();
        List<Map<String, Object>> studentList = new ArrayList<>();


        for (Student student : students) {
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("id", student.getStudentId());
            studentMap.put("name", student.getStudentName());
            
            for (Paper paper: student.getPaperList()) {
                studentMap.put(Constant.course[paper.getCourseId()-1], paper.getScore());
            }
            if(studentMap.get("net") == null) {
                studentMap.put("net", null);
            }
            if(studentMap.get("os") == null) {
                studentMap.put("os", null);
            }
            if(studentMap.get("compo") == null) {
                studentMap.put("compo", null);
            }
            if(studentMap.get("ds") == null) {
                studentMap.put("ds", null);
            }

            studentList.add(studentMap);
        }

        /////////// 将students转为json格式返回
        ObjectMapper mapper = new ObjectMapper();

        Result<List<Map<String, Object>>> responseBody = new Result<>();

        try {
            responseBody.setCode("200");
            responseBody.setMessage("success");
            responseBody.setData(studentList);
            System.out.println(mapper.writeValueAsString(studentList));
            return mapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseBody.setCode("500");
        responseBody.setMessage("error");
        return responseBody.toString();

    }


    // @GetMapping("/studentsInformation")
    // @CrossOrigin
    // @ResponseBody
    // public String getStudentsInformation(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    //     Paging<Student> students = studentService.findAll(pageNum, pageSize);


    //     /////////// 将students转为json格式返回
    //     ObjectMapper mapper = new ObjectMapper();

    //     Result<String> responseBody = new Result<>();

    //     try {
    //         String studentsJson = mapper.writeValueAsString(studentList);
    //         responseBody.setCode("200");
    //         responseBody.setMessage("success");
    //         responseBody.setData(studentsJson);
    //         return mapper.writeValueAsString(responseBody);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     responseBody.setCode("500");
    //     responseBody.setMessage("error");
    //     return responseBody.toString();
    // }

    @GetMapping("/student")
    @CrossOrigin
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

    @PostMapping("/changePassword")
    @CrossOrigin
    @ResponseBody
    public String postMethodName(@RequestBody String changInformation) {
        System.out.println(changInformation);
        // TODO: process POST request
        ///////////// changeInformation是一个json对象，转为map
        ObjectMapper mapper = new ObjectMapper();
        Result<String> responseBody = new Result<>();

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> adminMap = mapper.readValue(changInformation, Map.class);
            System.out.println(adminMap);
            String adminName = adminMap.get("adminName");
            String oldPassword = adminMap.get("oldPassword");
            System.out.println("oldPassword: " + oldPassword);
            String newPassword = adminMap.get("newPassword");

            Student student = studentService.getStudentById(adminName);
            if (student == null) {
                Admin admin = adminService.selectByAdminName(adminName);
                if (admin == null) {
                    responseBody.setCode(Constant.CODE_401);
                    responseBody.setMessage("用户名不存在");
                    return mapper.writeValueAsString(responseBody);
                }
                if (!admin.getSaltPassword().equals(oldPassword)) {
                    responseBody.setCode(Constant.CODE_401);
                    responseBody.setMessage("旧密码错误");
                    return mapper.writeValueAsString(responseBody);
                }
                adminService.updatePassword(adminName, newPassword);
                responseBody.setCode("200");
                responseBody.setMessage("修改密码成功");
                return mapper.writeValueAsString(responseBody);
            }
            if (!student.getSaltPassword().equals(oldPassword)) {
                System.out.println("TrueoldPassword: " + student.getSaltPassword());
                System.out.println(oldPassword);
                responseBody.setCode(Constant.CODE_401);
                responseBody.setMessage("旧密码错误");
                return mapper.writeValueAsString(responseBody);
            }
            studentService.updatePassword(student.getStudentId(), newPassword);

            responseBody.setCode("200");
            responseBody.setMessage("修改密码成功");
            return mapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseBody.setCode("500");
        responseBody.setMessage("error");
        return responseBody.toString();
    }

    @GetMapping("/student/exams")
    @CrossOrigin
    @ResponseBody
    public String getStudentExams(@RequestParam String studentId) {
    
        String studentName = studentService.getStudentById(studentId).getStudentName();
        List<Paper> exams = paperService.getPaperByStudentId(studentId);

        Map<String, Object> studentMap = new HashMap<>();
        studentMap.put("studentId", studentId);
        studentMap.put("studentName", studentName);
        List<Map<String, Integer>> examList = new ArrayList<>();

        for (int i = 0; i < exams.size(); i++) {
            Paper exam = exams.get(i);
            Map<String, Integer> examMap = new HashMap<>();
            examMap.put("courseId", exam.getCourseId());
            examMap.put("score", exam.getScore());
            examList.add(examMap);
        }
        studentMap.put("exams", examList);

        ObjectMapper mapper = new ObjectMapper();
        Result<String> responseBody = new Result<>();
        try {
            responseBody.setCode("200");
            responseBody.setMessage("success");
            responseBody.setData(mapper.writeValueAsString(studentMap));
            return mapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseBody.setCode("500");
        responseBody.setMessage("error");
        return responseBody.toString();

    }

    @GetMapping("/student/insert")
    @CrossOrigin
    @ResponseBody
    public String addOneStudent(@RequestParam String studentId, @RequestParam String saltPassword, @RequestParam String studentName) {
        
        studentService.register(studentId, saltPassword, studentName);
           
        // TODO: process POST request

        Result<String> responseBody = new Result<>();
        responseBody.setCode("200");
        responseBody.setMessage("success");

        return responseBody.toString();
    }

    @DeleteMapping("/student/delete")
    @CrossOrigin
    @ResponseBody
    public String deleteStudent(@RequestParam String studentId) {
        studentService.deleteStudent(studentId);
        Result<String> responseBody = new Result<>();
        responseBody.setCode("200");
        responseBody.setMessage("success");
        responseBody.setSuccess(true);
        return responseBody.toString();
    }

}
