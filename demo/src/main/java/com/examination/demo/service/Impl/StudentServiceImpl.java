package com.examination.demo.service.Impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.demo.dao.StudentDAO;
import com.examination.demo.dataobject.CourseDO;
import com.examination.demo.dataobject.PaperDO;
import com.examination.demo.dataobject.StudentDO;
import com.examination.demo.model.Course;
import com.examination.demo.model.Paper;
import com.examination.demo.model.Student;
import com.examination.demo.service.CourseService;
import com.examination.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
    //////////根据上面的dao层的方法，实现学生的增删改查
    @Autowired
    private StudentDAO studentDAO;

    public Student getStudentById(String studentId) {
        StudentDO studentDO = studentDAO.getStudentById(studentId);
        
        if (studentDO == null) {
            return null;
        }
        Student student = StudentService.toModel(studentDO);

        return student;
    }

    @SuppressWarnings("null")
    public Student getStudentByName(String studentName) {
        StudentDO studentDO =  studentDAO.getStudentByName(studentName);
        Student student = new Student();
        BeanUtils.copyProperties(studentDO, student);
        
        return student;
    }

    @SuppressWarnings("null")
    public void insertStudent(Student student) {
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(student, studentDO);
        studentDAO.insertStudent(studentDO);
    }

    @SuppressWarnings("null")
    public void updateStudent(Student student) {
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(student, studentDO);
        studentDAO.updateStudent(studentDO);
    }

    public void deleteStudent(String studentId) {
        studentDAO.deleteStudent(studentId);
    }

    public Student login(String studentName, String saltPassword) {
        StudentDO studentDO = studentDAO.login(studentName, saltPassword);
        if(studentDO == null){
            return null;
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentDO, student);
        return student;
    }

    public void register(String studentId, String studentName, String saltPassword) {
        studentDAO.insertStudent(new StudentDO(studentId, studentName, saltPassword));
    }

    public void updatePassword(String studentName, String saltPassword) {
        studentDAO.updatePassword(studentName, saltPassword);
    }

    public List<Student> getAllStudent(){
        List<StudentDO> studentDOList = studentDAO.getAllStudent();
        if(studentDOList.size() == 0){
            System.out.println("没有学生信息");
            return null;
        }
        List<Student> studentList = new ArrayList<>();
        for(StudentDO studentDO : studentDOList){
            Student student = StudentService.toModel(studentDO);
            studentList.add(student);
        }
        return studentList;
    }


    public Student toModel(StudentDO studentDO){
        Student student = new Student();
        student.setStudentId(studentDO.getStudentId());
        student.setStudentName(studentDO.getStudentName());
        if (studentDO.getSaltPassword() != null){
            student.setSaltPassword(studentDO.getSaltPassword());
        }
        
        if (studentDO.getCourseList() != null){
           List<CourseDO> courseDOList = studentDO.getCourseList();
           List<Course> courseList = new ArrayList<>();
           for (CourseDO courseDO : courseDOList){
                Course course = CourseService.toModel(courseDO);
                courseList.add(course);
            }
            student.setCourseList(courseList);
        }
        
        if(studentDO.getPaperList() != null){
            List<PaperDO> paperDOList = studentDO.getPaperList();
            List<Paper> paperList = new ArrayList<>();
            for (PaperDO paperDO : paperDOList){
                Paper paper = new Paper();
                paper.setPaperId(paperDO.getPaperId());
                paper.setCourseId(paperDO.getCourseId());
                paper.setStudentId(paperDO.getStudentId());
                paper.setScore(paperDO.getScore());
                paper.setCreateTime(paperDO.getCreateTime());
                paper.setValidateTime(paperDO.getValidateTime());
                
                paperList.add(paper);
            }
        }
      

        return student;
    }
    
    
}
