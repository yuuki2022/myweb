package com.examination.demo.app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.common.Constant;
import com.examination.demo.model.Admin;
import com.examination.demo.model.Course;
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
    @ResponseBody
    public String getStudents() {
        List<Student> students = studentService.getAllStudent();
        List<Map<String, Object>> studentList = new ArrayList<>();

        for (Student student : students) {
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("studentId", student.getStudentId());
            studentMap.put("studentName", student.getStudentName());
            List<Map<String, Integer>> courses = new ArrayList<>();

            if (student.getCourseList() != null) {
                for (Course course : student.getCourseList()) {
                    Map<String, Integer> courseMap = new HashMap<>();
                    courseMap.put("courseId", course.getCourseId());
                    courseMap.put("score", 0);
                    for (Paper paper : student.getPaperList()) {
                        if (paper.getCourseId() == course.getCourseId()) {
                            courseMap.put("score", paper.getScore());
                        }
                    }
                    courses.add(courseMap);
                }
            }

            studentMap.put("courses", courses);
            studentList.add(studentMap);
        }

        /////////// 将students转为json格式返回
        ObjectMapper mapper = new ObjectMapper();

        Result<String> responseBody = new Result<>();

        try {
            String studentsJson = mapper.writeValueAsString(studentList);
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
        System.out.println("学生的学号是");
        System.out.println(studentId);
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

    @PostMapping("/student/insert")
    @ResponseBody
    public String addOneStudent(@RequestParam String studentId, @RequestParam String studentName,
            @RequestBody String courseInformation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            @SuppressWarnings("unchecked")
            List<Integer> courseList = mapper.readValue(courseInformation, List.class);
            studentService.register(studentId, "123456", studentName);
            for (Integer courseId : courseList) {
                studentService.insertCourseStudent(studentId, courseId);
            }
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO: process POST request

        Result<String> responseBody = new Result<>();
        responseBody.setCode("200");
        responseBody.setMessage("success");

        return responseBody.toString();
    }

    @DeleteMapping("/student/delete")
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
