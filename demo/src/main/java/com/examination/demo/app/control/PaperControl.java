package com.examination.demo.app.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.model.Question;
import com.examination.demo.service.PaperService;
import com.examination.demo.service.QuestionService;
import com.examination.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class PaperControl {
    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;
    

    @GetMapping("/getPaper")
    @ResponseBody
    public String getMyPaper(@RequestParam Integer courseId, @RequestParam String studentId) {
        ObjectMapper mapper = new ObjectMapper();
        paperService.insertPaper(courseId, studentId);
        List<Question> questions = questionService.createOnePaper(courseId);
        for (Question question : questions) {
            paperService.insertPaperQuestion(courseId, question.getQuestionId());
        }

        String studentName = studentService.getStudentById(studentId).getStudentName();
        Map<String, Object> testInformation = new HashMap<>();
        testInformation.put("course_id",courseId);
        testInformation.put("studentId", studentId);
        testInformation.put("studentName", studentName);
        testInformation.put("questions", questions);
        testInformation.put("testMinutes", 40);
        String testInformationJson = null;
        try {
            testInformationJson = mapper.writeValueAsString(testInformation);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return testInformationJson;
    }


    @GetMapping("/submitPaper")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
}
