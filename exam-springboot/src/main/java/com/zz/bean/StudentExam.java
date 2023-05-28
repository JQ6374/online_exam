package com.zz.bean;

import java.time.LocalDateTime;

public class StudentExam {
    private Integer seId;
    private Integer uId;
    private Integer eId;
    private String rightStudentAnswer;

    private LocalDateTime createTime;

    public StudentExam() {
    }

    public StudentExam(Integer uId, Integer eId, String rightStudentAnswer) {
        this.uId = uId;
        this.eId = eId;
        this.rightStudentAnswer = rightStudentAnswer;
    }

    @Override
    public String toString() {
        return "StudentExam{" +
                "seId=" + seId +
                ", uId=" + uId +
                ", eId=" + eId +
                ", rightStudentAnswer='" + rightStudentAnswer + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getRightStudentAnswer() {
        return rightStudentAnswer;
    }

    public void setRightStudentAnswer(String rightStudentAnswer) {
        this.rightStudentAnswer = rightStudentAnswer;
    }
}
