package com.examination.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.examination.demo.dataobject.CourseDO;
import com.examination.demo.dataobject.PaperDO;
import com.examination.demo.dataobject.StudentDO;
import com.examination.demo.model.Course;
import com.examination.demo.model.Paper;
import com.examination.demo.model.Student;


public interface StudentService {
    public Student login(String studentId, String saltPassword);
    
    public void register(String studentId, String saltPassword, String studentName);
    
    public void updatePassword(String studentId, String saltPassword);

    public Student getStudentById(String studentId);

    public Student getStudentByName(String studentName);

    public void insertStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(String studentId);

    public void insertCourseStudent(String studentId, Integer courseId);

    public List<Student> getAllStudent();
    
    public static Student toModel(StudentDO studentDO){
        Student student = new Student();
        student.setStudentId(studentDO.getStudentId());
        student.setStudentName(studentDO.getStudentName());
        if (studentDO.getSaltPassword() != null) {
            student.setSaltPassword(studentDO.getSaltPassword());
        }
        if (studentDO.getCourseList() != null) {
           List<Course> courseList = new ArrayList<>();
           List<CourseDO> courseDOList = studentDO.getCourseList();
           
           for (CourseDO courseDO : courseDOList) {
                courseList.add(CourseService.toModel(courseDO));
             }
           student.setCourseList(courseList);
        }


        if (studentDO.getPaperList() != null) {
            List<Paper> paperList = new ArrayList<>();
            List<PaperDO> paperDOList = studentDO.getPaperList();
            for (PaperDO paperDO : paperDOList) {
                paperList.add(PaperService.toModel(paperDO));
            }
            student.setPaperList(paperList);
        }
        return student;
    }
} 
