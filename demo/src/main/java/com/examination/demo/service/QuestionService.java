package com.examination.demo.service;

import java.util.List;

import com.examination.demo.dataobject.QuestionDO;
import com.examination.demo.model.Question;

public interface QuestionService {

    public void insertQuestion(Question question);

    public void deleteQuestion(Long questionId);

    public List<Question> getQuestionsByCourseId(Integer courseId);

    public List<Question> createOnePaper(Integer courseId);

    public Integer getRightAnswer(Long questionId, String rightAnswer);


    public static QuestionDO toDataObject(Question question){
        QuestionDO questionDO = new QuestionDO();
        questionDO.setCourseId(question.getCourseId());
        questionDO.setContent(question.getContent());
        if(question.getAnswer() != null){
            questionDO.setAnswer1(question.getAnswer()[0]);
            questionDO.setAnswer2(question.getAnswer()[1]);
            questionDO.setAnswer3(question.getAnswer()[2]);
            questionDO.setAnswer4(question.getAnswer()[3]);
        }
        questionDO.setRightAnswer(question.getRightAnswer());
        questionDO.setHard(question.getHard());
        return questionDO;
    } 

    public static Question toModel(QuestionDO questionDO){
        Question question = new Question();
        question.setQuestionId(questionDO.getQuestionId());
        question.setCourseId(questionDO.getCourseId());
        question.setContent(questionDO.getContent());
        String[] answers = new String[4];
        answers[0] = questionDO.getAnswer1();
        answers[1] = questionDO.getAnswer2();
        answers[2] = questionDO.getAnswer3();
        answers[3] = questionDO.getAnswer4();
        ///////////打乱answers数组
        for(int i = 0; i < 4; i++){
            int j = (int)(Math.random() * 4);
            String temp = answers[i];
            answers[i] = answers[j];
            answers[j] = temp;
        }
        question.setAnswer(answers);
        question.setRightAnswer(questionDO.getRightAnswer());
        question.setHard(questionDO.getHard());
        return question;
    }
    
} 