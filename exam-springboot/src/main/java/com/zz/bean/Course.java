package com.zz.bean;

import java.time.LocalDateTime;

public class Course {

    private Integer cId;
    private Integer uId;
    private String name;
    private String courseCode;
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", uId=" + uId +
                ", name='" + name + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
