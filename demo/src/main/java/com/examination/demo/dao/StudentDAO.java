package com.examination.demo.dao;

import com.examination.demo.dataobject.StudentDO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



@Mapper
public interface StudentDAO{
    
    @Select("select student_id as studentId, student_name as studentName, salt_password as saltPassword from student where student_id = #{studentId}")
    StudentDO getStudentById(String studentId);

    @Select("select * from student where student_name = #{studentName} limit 1")
    StudentDO getStudentByName(String studentName);

    @Insert("insert into student(student_id, student_name, salt_password) values(#{studentId}, #{studentName}, #{saltPassword})")
    void insertStudent(StudentDO studentDO);

    @Insert("insert into course_student(student_id, course_id) values(#{studentId}, #{courseId})")
    void insertCourseStudent(String studentId, Integer courseId);

    @Update("update student set student_name = #{studentName}, salt_password = #{saltPassword} where student_id = #{studentId}")
    void updateStudent(StudentDO studentDO);

    @Delete("delete from student where student_id = #{studentId}")
    void deleteStudent(String studentId);

    @Select("select * from student where student_Id = #{studentId} and salt_password = #{saltPassword}")
    StudentDO login(String studentName, String saltPassword);


    @Update("update student set salt_password = #{saltPassword} where student_id = #{studentId}")
    void updatePassword(String studentId, String saltPassword);

    //////////////获取所有学生的信息
    @Select("select student_id as studentId, salt_password as saltPassword, student_name as studentName from student")
    List<StudentDO> getAllStudent();


   

}