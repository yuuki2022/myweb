package com.examination.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class Student {
   String studentId;
  
   String studentName;
  
   String saltPassword;

   List<Course> courseList;

   List<Paper> paperList;




}
