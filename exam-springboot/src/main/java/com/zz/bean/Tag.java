package com.zz.bean;

public class Tag {
    private Integer tagId;
    private Integer uId;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", uId=" + uId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
}
