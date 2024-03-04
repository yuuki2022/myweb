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
    @Select("select course_id as courseId, course_name as courseName from course where course_id in (select course_id from course_student where student_id = #{studentId})")
    List<CourseDO> getCoursesByStudentId(String studentId);
    
    @Insert("insert into course(courseName) values(#{courseName})")
    void insertCourse(CourseDO courseDO);
    
}
