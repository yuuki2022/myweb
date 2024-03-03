package com.examination.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.examination.demo.dataobject.QuestionDO;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface QuestionDAO {

    @Insert("insert into question(course_id, content, answer1, answer2, answer3, answer4, right_answer, hard)" + 
            "values(#{courseId}, #{content}, #{answer1}, #{answer2}, #{answer3}, #{answer4}, #{rightAnswer}, #{hard})")
    //////SQL语句分行
    void insertQuestion(QuestionDO questionDO);

    @Select("select question_id as questionId, course_id as courseId, content, answer1,answer2,answer3,answer4, right_answer as rightAnswer, hard from question where course_id = #{courseId} and hard = 0 order by rand() limit 35")
    List<QuestionDO> getQuestionsByCourseId(@Param("courseId") Integer courseId);

    //////随机获取当5课程的题目（50个）, qhard为难度, 难度为0,1,2. 分别随机占比为7:2:1
    @Select("select question_id as questionId, course_id as courseId, content, answer1,answer2,answer3,answer4, hard from question where course_id = #{courseId} and hard = 0 order by rand() limit 35")
    List<QuestionDO> getQuestionsByCourseIdAndHard0(@Param("courseId") Integer courseId);

    @Select("select question_id as questionId, course_id as courseId, content, answer1,answer2,answer3,answer4, hard from question where course_id = #{courseId} and hard = 1 order by rand() limit 10")
    List<QuestionDO> getQuestionsByCourseIdAndHard1(@Param("courseId") Integer courseId);

    @Select("select question_id as questionId, course_id as courseId, content, answer1,answer2,answer3,answer4, hard from question where course_id = #{courseId} and hard = 2 order by rand() limit 5")
    List<QuestionDO> getQuestionsByCourseIdAndHard2(@Param("courseId") Integer courseId);
    
    @Select("select count(*) from question where question_id = #{questionId} and right_answer = #{rightAnswer}")
    Integer getRightAnswer(@Param("questionId") Long questionId, @Param("rightAnswer") String rightAnswer);

    @Delete("delete from question where question_id = #{questionId}")
    void deleteQuestion(@Param("questionId") Long questionId);

}
