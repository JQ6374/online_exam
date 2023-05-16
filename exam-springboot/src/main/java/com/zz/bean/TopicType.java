package com.zz.bean;

public class TopicType {
    private Integer typeId;
    private String name;

    @Override
    public String toString() {
        return "TopicType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
