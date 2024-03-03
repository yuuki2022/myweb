package com.examination.demo.service;

import java.util.List;

import com.examination.demo.dataobject.PaperDO;
import com.examination.demo.model.Paper;
import com.examination.demo.model.Question;

// @Insert("insert into paper_question(paper_id, question_id) values(#{paperId}, #{questionId})")
// void insertPaperQuestion(Integer paperId, Integer questionId);

// ///////////created_time是当前时间
// @Insert("insert into paper(course_id, student_id, create_time, validate_time ,score)" +   
//         "values(#{courseId}, #{studentId}, now(), now(),#{score})")
// void insertPaper(Integer paperId, Integer courseId, String studentId, Integer score);

// @Update("update paper set score = #{score} where paper_id = #{paperId}")
// void updatePaperScore(Integer paperId, Integer score);


// @Delete("delete from paper where paper_id = #{paperId}")
// void deletePaper(Integer paperId);


// @Select("select * from paper where student_id = #{studentId}")
// List<PaperDO> getPaperById(Integer studentId);
public interface PaperService {
    public void insertPaperQuestion(Integer paperId, Long questionId);

    public void insertPaper(Integer courseId, String studentId);

    public void updatePaperScore(Integer paperId, Integer score);

    public void deletePaper(Integer paperId);

    public List<Paper> getPaperById(Integer studentId);

    public static Paper toModel(PaperDO paperDO){
        Paper paper = new Paper();
        paper.setPaperId(paperDO.getPaperId());
        paper.setCourseId(paperDO.getCourseId());
        paper.setStudentId(paperDO.getStudentId());
        paper.setCreateTime(paperDO.getCreateTime());
        paper.setValidateTime(paperDO.getValidateTime());
        paper.setScore(paperDO.getScore());
        return paper;
    }

   
    
} 