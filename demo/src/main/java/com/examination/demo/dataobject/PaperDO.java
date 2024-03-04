package com.examination.demo.dataobject;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class PaperDO {
    private Integer paperId;

    private Integer courseId;
    
    private String studentId;

    private Integer score;


    public PaperDO(Integer paperId, Integer courseId, String studentId, Integer score) {
        this.paperId = paperId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
    }
}
