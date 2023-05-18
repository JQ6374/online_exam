package com.zz.bean;

import java.time.LocalDateTime;

public class Papers {
    private Integer pId;
    private String name;
    private String content;
    private LocalDateTime createTime;

    public Papers() {
    }

    public Papers(String name, String content, LocalDateTime createTime) {
        this.name = name;
        this.content = content;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Papers{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
