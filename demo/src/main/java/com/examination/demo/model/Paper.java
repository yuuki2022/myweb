package com.examination.demo.model;

import java.time.LocalDateTime;
import java.util.List;


import lombok.Data;


@Data
public class Paper {
    private Integer paperId;

    private Integer courseId;
    
    private String studentId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime validateTime;
    
    private Integer score;

    List<Question> questionList;


}
