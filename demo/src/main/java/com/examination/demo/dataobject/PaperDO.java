package com.examination.demo.dataobject;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class PaperDO {
    private Integer paperId;

    private Integer courseId;
    
    private String studentId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime validateTime;
    
    private Integer score;

    List<QuestionDO> questionList;

    

    public PaperDO(Integer paperId, Integer courseId, String studentId, LocalDateTime createTime, LocalDateTime validateTime, Integer score, List<QuestionDO> questionList) {
        this.paperId = paperId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.createTime = createTime;
        this.validateTime = validateTime;
        this.score = score;
        this.questionList = questionList;
    }
}
