package com.zz.bean;

public class Papers {
    private Integer pId;
    private String name;
    private String content;

    @Override
    public String toString() {
        return "Papers{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
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
