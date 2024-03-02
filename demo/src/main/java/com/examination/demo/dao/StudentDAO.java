package com.examination.demo.dao;

import com.examination.demo.dataobject.StudentDO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



@Mapper
public interface StudentDAO{
    
    @Select("select * from student where studentId = #{studentId}")
    StudentDO getStudentById(String studentId);

    @Select("select * from student where studentName = #{studentName}")
    StudentDO getStudentByName(String studentName);

    @Insert("insert into student(studentId, studentName, saltPassword) values(#{studentId}, #{studentName}, #{saltPassword})")
    void insertStudent(StudentDO studentDO);

    @Update("update student set studentName = #{studentName}, saltPassword = #{saltPassword} where studentId = #{studentId}")
    void updateStudent(StudentDO studentDO);

    @Delete("delete from student where studentId = #{studentId}")
    void deleteStudent(String studentId);


}