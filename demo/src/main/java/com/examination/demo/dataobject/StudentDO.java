package com.examination.demo.dataobject;

import java.util.List;


import lombok.Data;

@Data
public class StudentDO {
    private String studentId;

    private String studentName;

    private String saltPassword;
   
    private List<CourseDO> courseList;

    private List<PaperDO> paperList;

    public StudentDO() {
        
    }

    public StudentDO(String studentId, String studentName, String saltPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.saltPassword = saltPassword;
    }

   

  
    
}
