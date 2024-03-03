package com.examination.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.examination.demo.dataobject.CourseDO;

@Mapper
public interface CourseDAO {
    @Select("select * from course where courseId = #{courseId}")
    CourseDO getCourseById(Integer courseId);

    //////////有三个表，学生表，课程表，学生课程表，学生课程表中有学生id和课程id，通过学生id查找课程id，再通过课程id查找课程
    @Select("select * from course where courseId in (select courseId from student_course where studentId = #{studentId})")
    List<CourseDO> getCoursesByStudentId(String studentId);
    
    @Insert("insert into course(courseName) values(#{courseName})")
    void insertCourse(CourseDO courseDO);
    
}
