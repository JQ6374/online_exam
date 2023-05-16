package com.zz.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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

}
