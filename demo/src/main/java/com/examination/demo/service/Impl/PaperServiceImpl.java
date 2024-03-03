package com.examination.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.demo.dao.PaperDAO;
import com.examination.demo.dataobject.PaperDO;
import com.examination.demo.model.Paper;
import com.examination.demo.service.PaperService;

// public void insertPaperQuestion(Integer paperId, Integer questionId);

// public void insertPaper(Integer paperId, Integer courseId, String studentId, Integer score);

// public void updatePaperScore(Integer paperId, Integer score);

// public void deletePaper(Integer paperId);

// public List<Paper> getPaperById(Integer studentId);

@Service
public class PaperServiceImpl implements PaperService{
  
    @Autowired
    private PaperDAO paperDAO;

    @Override
    public void insertPaperQuestion(Integer paperId, Integer questionId) {
        paperDAO.insertPaperQuestion(paperId, questionId);
    }

    @Override
    public void insertPaper(Integer courseId, String studentId, Integer score) {
        paperDAO.insertPaper(courseId, studentId, score);
    }

    @Override
    public void updatePaperScore(Integer paperId, Integer score) {
        paperDAO.updatePaperScore(paperId, score);
    }

    @Override
    public void deletePaper(Integer paperId) {
        // TODO Auto-generated method stub
        paperDAO.deletePaper(paperId);
    }

    @Override
    public List<Paper> getPaperById(Integer studentId) {
        List<PaperDO> paperDOs = paperDAO.getPaperById(studentId);
        List<Paper> papers = new ArrayList<>();
        for(PaperDO paperDO : paperDOs) {
            Paper paper = PaperService.toModel(paperDO);
            papers.add(paper);
        }

        return papers;
    }
    
}
