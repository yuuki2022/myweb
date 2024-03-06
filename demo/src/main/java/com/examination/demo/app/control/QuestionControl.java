package com.examination.demo.app.control;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examination.demo.common.Constant;
import com.examination.demo.model.Question;
import com.examination.demo.model.Result;
import com.examination.demo.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class QuestionControl {
    @Autowired
    private QuestionService questionService;

    @GetMapping("questions")
    @ResponseBody
    @CrossOrigin
    public String getQuestions(@RequestParam Integer courseId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        List<Question> questions = questionService.createOnePaper(courseId);

        ///////////////////打乱questions的次序
        for (int i = 0; i < questions.size(); i++) {
            int index = (int) (Math.random() * questions.size());
            Question temp = questions.get(i);
            questions.set(i, questions.get(index));
            questions.set(index, temp);
        }
        /////////////////建立一个json对象
        map.put("questions", questions);
        map.put("time", 30);

        String questionsJson = mapper.writeValueAsString(map);

        return questionsJson;
    }
    

    @PostMapping("/createQuestion")
    @CrossOrigin
    @ResponseBody
    public String createQuestion(@RequestBody String questionJson) {
        //TODO: process POST request
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(questionJson);
            Question question = mapper.readValue(questionJson, Question.class);
            System.out.println(mapper.writeValueAsString(question));
            questionService.insertQuestion(question);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Result<String> result = new Result();
        result.setCode(Constant.CODE_200);
        result.setMessage("success");
        result.setSuccess(true);

        return result.toString();
    }
    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    


  
    
}
