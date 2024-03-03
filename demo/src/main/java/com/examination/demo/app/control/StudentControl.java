package com.examination.demo.app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.common.Constant;
import com.examination.demo.model.Course;
import com.examination.demo.model.Paper;
import com.examination.demo.model.Result;
import com.examination.demo.model.Student;
import com.examination.demo.service.CourseService;
import com.examination.demo.service.PaperService;
import com.examination.demo.service.StudentService;
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
    private CourseService courseService;

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

@PostMapping("/changePassword")
@ResponseBody
public String postMethodName(@RequestBody String changInformation) {
    System.out.println(changInformation);
    //TODO: process POST request
    /////////////changeInformation是一个json对象，转为map
    ObjectMapper mapper = new ObjectMapper();
    Result<String> responseBody = new Result<>();

    try {
        @SuppressWarnings("unchecked")
        Map<String,String> studentMap = mapper.readValue(changInformation, Map.class);
        System.out.println(studentMap);
        String studentId = studentMap.get("studentId");
        String oldPassword = studentMap.get("oldPassword");
        System.out.println("oldPassword: "+oldPassword);
        String newPassword = studentMap.get("newPassword");

        Student student = studentService.getStudentById(studentId);
        if(student == null){
            responseBody.setCode(Constant.CODE_401);
            responseBody.setMessage("没有找到学生");
            return mapper.writeValueAsString(responseBody);
        }
        if(!student.getSaltPassword().equals(oldPassword)){
            System.out.println("TrueoldPassword: "+student.getSaltPassword());
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
@ResponseBody
public String getStudentExams(@RequestParam String studentId) {
    System.out.println(studentId);
    String studentName = studentService.getStudentById(studentId).getStudentName();
    List<Paper> exams = paperService.getPaperByStudentId(studentId);

    Map<String,Object> studentMap = new HashMap<>();
    studentMap.put("studentId", studentId);
    studentMap.put("studentName", studentName);
    List<Map<String,Integer>> examList = new ArrayList<>();

    for(int i = 0 ; i < exams.size(); i++){
        Paper exam = exams.get(i);
        Map<String, Integer> examMap = new HashMap<>();
        examMap.put("courseId", exam.getScore());
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


    
}
