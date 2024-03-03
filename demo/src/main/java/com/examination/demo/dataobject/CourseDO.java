package com.examination.demo.dataobject;


import java.util.List;

import lombok.Data;

@Data
public class CourseDO {
    Integer courseId;

    String courseName;

    List<QuestionDO> questionList;
    
    public CourseDO() {
        
    }
    public CourseDO(Integer courseId, String courseName, List<QuestionDO> questionList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.questionList = questionList;
    }
    
}
