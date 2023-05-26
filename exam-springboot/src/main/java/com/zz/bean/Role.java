package com.zz.bean;

public class Role {
    private Integer rId;
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
