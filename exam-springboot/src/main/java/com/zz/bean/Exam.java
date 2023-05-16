package com.zz.bean;

import java.time.LocalDateTime;

public class Exam {
    private Integer eId;
    private Integer cId;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private Integer isExist;

    @Override
    public String toString() {
        return "Exam{" +
                "eId=" + eId +
                ", cId=" + cId +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", isExist=" + isExist +
                '}';
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}
