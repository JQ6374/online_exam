package com.zz.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Integer uId;
    private Integer rId;
    private String username;
    private String password;
    private String email;
    private String emailCode;
    private LocalDateTime registerTime;

}
