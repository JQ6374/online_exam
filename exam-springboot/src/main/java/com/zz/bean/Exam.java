package com.zz.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Exam {
    private Integer e_id;
    private Integer c_id;
    private String name;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String status;
}
