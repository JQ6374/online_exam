package com.zz.controller;

import com.zz.Service.UserService;
import com.zz.bean.User;
import com.zz.utils.result.ApiResult;
import com.zz.utils.Code;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResult register(@RequestBody User user) {
        user.setRegisterTime(LocalDateTime.now());
        TempResult tempResult = userService.register(user);
        ApiResult apiResult = new ApiResult();
        if (tempResult.isFlag()) {
            apiResult.setCode(Code.SAVA_OK);
        } else {
            apiResult.setCode(Code.SAVA_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
        return apiResult;
    }

    @PostMapping("/login")
    public ApiResult login(@RequestBody User user) {
        TempResult tempResult = userService.login(user);
        return new ApiResult(Code.GET_OK, null, tempResult.getMsg());
    }

    @PostMapping("/updatePassword")
    public ApiResult updatePassword(@RequestBody User user) {
        TempResult tempResult = userService.updatePassword(user);
        return new ApiResult(Code.UPDATE_OK, null, tempResult.getMsg());
    }
}
