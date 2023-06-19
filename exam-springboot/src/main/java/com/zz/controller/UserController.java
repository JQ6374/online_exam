package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.service.CourseService;
import com.zz.service.UserService;
import com.zz.bean.User;
import com.zz.utils.result.ApiResult;
import com.zz.utils.Code;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    /**
     * @param uId 该uid为教师的uid 通过教师uid获取cid，通过cid获取学生的uid，获取到所有的学生信息
     * @return 返回学生信息列表
     */
    @GetMapping("/student/{uId}")
    public ApiResult<List<JSONObject>> getStudentList(@PathVariable("uId") Integer uId) {
        return courseService.selectStudentAndCourse(uId);
    }

    /**
     * 根据学号或者姓名搜索
     *
     * @param uId 教师Id
     * @param studentIdOrName 学生Id或者姓名
     */
    @GetMapping("/searchStudent/{uId}/{studentIdOrName}")
    public ApiResult<List<JSONObject>> searchStudentList(@PathVariable("uId") Integer uId,
                                                         @PathVariable("studentIdOrName") String studentIdOrName) {
        return courseService.searchStudentAndCourse(uId, studentIdOrName);
    }

    @DeleteMapping("/deleteStudentByCourse/{ucId}")
    public ApiResult<Object> deleteStudentByCourse(@PathVariable Integer ucId) {
        return courseService.deleteStudentByCourse(ucId);
    }

    @PostMapping("/register")
    public ApiResult<Object> register(@RequestBody User user) {
        user.setRegisterTime(LocalDateTime.now());
        TempResult tempResult = userService.register(user);
        return new ApiResult<>(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }

    @PostMapping("/login")
    public ApiResult<JSONObject> login(@RequestBody User user) {
        return userService.login(user);
    }

    @DeleteMapping("/logout/{uId}")
    public ApiResult<Object> logout(@PathVariable String uId) {
        return userService.logout(uId);
    }

    @PutMapping("/updatePassword")
    public ApiResult<Object> updatePassword(@RequestBody User user) {
        TempResult tempResult = userService.updatePassword(user);
        return new ApiResult<>(Code.UPDATE_OK, null, tempResult.getMsg());
    }
}
