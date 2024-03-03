package com.examination.demo.service;

import java.util.List;

import com.examination.demo.dataobject.CourseDO;
import com.examination.demo.model.Course;

public interface CourseService {
    public void insertCourse(String courseName);
    
    public Course getCourseById(String courseId);
    
    public List<Course> getCoursesByStudentId(String studentId);    

    public static Course toModel(CourseDO courseDO){
        Course course = new Course();
        course.setCourseId(courseDO.getCourseId());
        course.setCourseName(courseDO.getCourseName());
        return course;
    }
}
