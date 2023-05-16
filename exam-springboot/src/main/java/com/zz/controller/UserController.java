package com.zz.controller;

import com.zz.Service.UserService;
import com.zz.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void register(@RequestBody User user) {
        System.out.println(user);
    }
}
