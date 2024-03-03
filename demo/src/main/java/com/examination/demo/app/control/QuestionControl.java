package com.examination.demo.app.control;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.examination.demo.model.Admin;
import com.examination.demo.model.Question;
import com.examination.demo.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class QuestionControl {
    @Autowired
    private QuestionService questionService;

    @GetMapping("questions")
    @ResponseBody
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
    
    
}
