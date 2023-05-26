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
    @Autowired
    private CourseController courseController;

    /**
     *
     * @param uid 该uid为教师的uid 通过教师uid获取cid，通过cid获取学生的uid，获取到所有的学生信息
     * @return 返回学生信息列表
     */
    @GetMapping("/Student/{uid}")
    public ApiResult getStudentList(@PathVariable("uid") String uid){
        int idTeacher = Integer.parseInt(uid);
        ApiResult apiResult = courseController.selectStudentAndCourse(idTeacher);
        return apiResult;
    }
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
        return userService.login(user);
    }

    @PutMapping("/updatePassword")
    public ApiResult updatePassword(@RequestBody User user) {
        TempResult tempResult = userService.updatePassword(user);
        return new ApiResult(Code.UPDATE_OK, null, tempResult.getMsg());
    }
}
