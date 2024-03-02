package com.examination.demo.dataobject;

import java.util.List;

import lombok.Data;

@Data
public class StudentDO {
    private String studentId;

    private String studentName;

    private String saltPassword;

    List<CourseDO> courseList;

    List<PaperDO> paperList;

    public StudentDO(String studentId, String studentName, String saltPassword, List<CourseDO> courseList) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.saltPassword = saltPassword;
        this.courseList = courseList;
    }

  
    
}
