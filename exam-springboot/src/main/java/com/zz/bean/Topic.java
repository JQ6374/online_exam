package com.zz.bean;

import java.time.LocalDateTime;

public class Topic {
    private Integer tId;
    private Integer uId;
    private Integer typeId;
    private String typeName;
    private Integer tagId;
    private String tagName;
    private Integer difficultyId;
    private String difficultyName;
    private String question;
    private String answer;
    private Integer status;
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "Topic{" +
                "tId=" + tId +
                ", uId=" + uId +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", difficultyId=" + difficultyId +
                ", difficultyName='" + difficultyName + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
