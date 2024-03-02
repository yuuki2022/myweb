package com.examination.demo.dataobject;

import lombok.Data;

@Data
public class QuestionDO {
    Long questionId;
    Integer courseId;
    String content;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String rightAnswer;
    Integer hard;
}
