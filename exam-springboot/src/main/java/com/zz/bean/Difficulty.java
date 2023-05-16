package com.zz.bean;

public class Difficulty {
    private Integer dId;
    private String name;

    @Override
    public String toString() {
        return "Difficulty{" +
                "dId=" + dId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
