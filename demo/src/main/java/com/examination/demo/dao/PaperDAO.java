package com.examination.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.examination.demo.dataobject.PaperDO;

@Mapper
public interface PaperDAO {
    ///////有一个表，试卷表，还有一个试题表，它们之间有一个试卷与试题多对多关系表，将一组选择到题目插入到试卷与试题多对多关系表
    
    @Insert("insert into paper_question(paper_id, question_id) values(#{paperId}, #{questionId})")
    void insertPaperQuestion(Integer paperId, Long questionId);

    ///////////created_time是当前时间
    @Insert("insert into paper(course_id, student_id, create_time, validate_time ,score)" +   
            "values(#{courseId}, #{studentId}, now(), DATE_ADD(now(), INTERVAL 30 MINUTE),#{score})")
    void insertPaper(Integer courseId, String studentId, Integer score);

    @Update("update paper set score = #{score} where paper_id = #{paperId}")
    void updatePaperScore(Integer paperId, Integer score);

    @Update("update paper set validate_time = now() where paper_id = #{paperId}")
    void updatePaperValidateTime(Integer paperId);


    @Delete("delete from paper where paper_id = #{paperId}")
    void deletePaper(Integer paperId);


    @Select("select * from paper where student_id = #{studentId}")
    List<PaperDO> getPaperById(Integer studentId);

} 