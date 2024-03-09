package com.examination.demo.app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.model.Paper;
import com.examination.demo.model.Question;
import com.examination.demo.model.Result;
import com.examination.demo.service.PaperService;
import com.examination.demo.service.QuestionService;
import com.examination.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Controller
public class PaperControl {
    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/findOnePaper")
    public String getMethodName(@RequestParam Integer courseId, @RequestParam String studentId) {
        return new String();
    }

    @GetMapping("/getPaper")
    @CrossOrigin
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
        testInformation.put("courseId", courseId);
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

    @SuppressWarnings("unchecked")
    @PostMapping("/submitPaper")
    @CrossOrigin
    @ResponseBody
    public String submitPaper(@RequestBody String answerJson) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(answerJson);

        Map<String, Object> map = mapper.readValue(answerJson, Map.class);
        String studentId = (String) map.get("studentId");
        Integer courseId = (Integer) map.get("courseId");
        List<Map<String,Object>> temp = (List<Map<String,Object>>)map.get("questions");

        List<Question> answersList = temp.stream().map(e -> {
            Question question = new Question();
            question.setQuestionId(Long.valueOf((Integer) e.get("questionId")));
            question.setRightAnswer((String) e.get("rightAnswer"));
            return question;
        }).collect(Collectors.toList());

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        Paper paper = paperService.getPaperByStudentIdAndCourseId(studentId, courseId);

        Result<String> responseBody = new Result<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 用于存储每个线程的结果
        List<Future<Integer>> futures = new ArrayList<>();

        for (Question answer : answersList) {
            // 提交一个任务到线程池
            Future<Integer> future = executor.submit(() -> {
                if (questionService.getRightAnswer(answer.getQuestionId(), answer.getRightAnswer()) > 0) {
                    return 2;
                } else {
                    return 0;
                }
            });

            futures.add(future);
        }

        // 计算总分数
        Integer score = 0;
        for (Future<Integer> future : futures) {
            try {
                // get 方法会阻塞，直到任务完成
                score += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
        paperService.updatePaperScore(paper.getPaperId(), score);
        paperService.updatePaperValidateTime(paper.getPaperId());
        ////// 返回结果

        responseBody.setCode("200");
        responseBody.setMessage("提交成功");
        responseBody.setData(score.toString());

        try {
            return mapper.writeValueAsString(responseBody);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        responseBody.setCode("500");
        responseBody.setMessage("error");
        return responseBody.toString();

    }

    // @SuppressWarnings("unchecked")
    // @GetMapping("/deletePaper")
    // @CrossOrigin
    // @ResponseBody
    // public String deletePaper(@RequestBody String paperJson) {
    //     ObjectMapper mapper = new ObjectMapper();
    
    //     List<Integer> paperIds;
    //     try {
    //         paperIds = mapper.readValue(paperJson, List.class);
    //         for (Integer paperId : paperIds) {
    //             Integer 
    //             paperService.deletePaper(paperId);
    //         }
    //     } catch (JsonProcessingException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
        
    //     Result<String> result = new Result<>();
    //     result.setCode("200");
    //     result.setMessage("success");
    //     return result.toString();
    // }
}
