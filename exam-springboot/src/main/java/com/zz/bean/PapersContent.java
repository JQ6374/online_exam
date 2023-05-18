package com.zz.bean;

/**
 * 用来存放单个题目内容，形成JSONObject存在到试卷中
 */
public class PapersContent {
    private Integer tId;
    private String question;
    private String answer;
    private Integer score;

    public PapersContent() {
    }

    public PapersContent(String question, String answer, Integer score) {
        this.question = question;
        this.answer = answer;
        this.score = score;
    }

    public PapersContent(Integer tId, String question, String answer) {
        this.tId = tId;
        this.question = question;
        this.answer = answer;
    }

    public PapersContent(Integer tId, String question, String answer, Integer score) {
        this.tId = tId;
        this.question = question;
        this.answer = answer;
        this.score = score;
    }

    @Override
    public String toString() {
        return "PapersContent{" +
                "tId=" + tId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
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
}
