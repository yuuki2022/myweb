package com.examination.demo.service.Impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.demo.dao.CourseDAO;
import com.examination.demo.dataobject.CourseDO;
import com.examination.demo.model.Course;
import com.examination.demo.service.CourseService;




@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDAO courseDAO;

    @Override
    public Course getCourseById(String courseId) {
        CourseDO courseDO = courseDAO.getCourseById(courseId);
        return CourseService.toModel(courseDO);
    }

    @Override
    public void insertCourse(String courseName) {
        CourseDO courseDO = new CourseDO();
        courseDO.setCourseName(courseName);
        courseDAO.insertCourse(courseDO);
    }
    
    @Override
    public List<Course> getCoursesByStudentId(String studentId) {
        List<CourseDO> courseDOs = courseDAO.getCoursesByStudentId(studentId);
        List<Course> courses = new ArrayList<>();
        for(CourseDO courseDO : courseDOs) {
            Course course = CourseService.toModel(courseDO);
            courses.add(course);
        }
        return courses;
    }

   
}
