package com.zz.bean;

import java.time.LocalDateTime;

public class Papers {
    private Integer pId;
    private Integer uId;
    private String name;
    private String content;
    private LocalDateTime createTime;
    private Integer isExist;

    public Papers() {
    }

    public Papers(Integer uId, String name, String content, LocalDateTime createTime) {
        this.uId = uId;
        this.name = name;
        this.content = content;
        this.createTime = createTime;
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
                ", uId=" + uId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isExist=" + isExist +
                '}';
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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
