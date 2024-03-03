package com.examination.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.demo.dao.QuestionDAO;
import com.examination.demo.dataobject.QuestionDO;
import com.examination.demo.model.Question;
import com.examination.demo.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public List<Question> getQuestionsByCourseId(Integer courseId) {
        List<QuestionDO> questionDOList = questionDAO.getQuestionsByCourseId(courseId);
        List<Question> questionList = new ArrayList<>();
        for(QuestionDO questionDO : questionDOList){
            questionList.add(QuestionService.toModel(questionDO));
        }
        return questionList;
    }

    @Override
    public void insertQuestion(Question question) {
        QuestionDO questionDO = QuestionService.toDataObject(question);
        questionDAO.insertQuestion(questionDO);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionDAO.deleteQuestion(questionId);
    }

    @Override
    public List<Question> createOnePaper(Integer courseId){
        List<QuestionDO> questionDOList = questionDAO.getQuestionsByCourseIdAndHard0(courseId);
        questionDOList.addAll(questionDOList.size(), questionDAO.getQuestionsByCourseIdAndHard1(courseId));
        questionDOList.addAll(questionDOList.size(), questionDAO.getQuestionsByCourseIdAndHard2(courseId));
        List<Question> questionList = new ArrayList<>();
        for(QuestionDO questionDO : questionDOList){
            questionList.add(QuestionService.toModel(questionDO));
        }
        return questionList;
    }

    
    
}
