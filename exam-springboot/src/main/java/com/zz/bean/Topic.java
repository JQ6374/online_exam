package com.zz.bean;

import java.time.LocalDateTime;

public class Topic {
    private Integer t_id;
    private Integer u_id;
    private Integer type_id;
    private Integer tag_id;
    private Integer difficulty_id;
    private String question;
    private String answer;
    private Integer status;
    private LocalDateTime create_time;

    @Override
    public String toString() {
        return "Topic{" +
                "t_id=" + t_id +
                ", u_id=" + u_id +
                ", type_id=" + type_id +
                ", tag_id=" + tag_id +
                ", difficulty_id=" + difficulty_id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                '}';
    }

    public Integer getDifficulty_id() {
        return difficulty_id;
    }

    public void setDifficulty_id(Integer difficulty_id) {
        this.difficulty_id = difficulty_id;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
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

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}
