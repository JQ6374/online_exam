package com.zz.bean;

import java.time.LocalDateTime;

public class Exam {
    private Integer eId;
    private Integer cId;
    private Integer pId;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private Integer isExist;
    private String content;

    @Override
    public String toString() {
        return "Exam{" +
                "eId=" + eId +
                ", cId=" + cId +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", isExist=" + isExist +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
