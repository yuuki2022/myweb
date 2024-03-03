package com.examination.demo.model;

import com.examination.demo.dataobject.QuestionDO;

import lombok.Data;

@Data
public class Question {

    Long questionId;

    Integer courseId;

    String content;

    String[] answer = new String[4];

    String rightAnswer;
    
    Integer hard;

    
}
