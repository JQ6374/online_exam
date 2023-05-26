package com.zz.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.UserService;
import com.zz.bean.User;
import com.zz.dao.UserDao;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import com.zz.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public TempResult register(User user) {
        TempResult result = emailUtils.emailCheck(user.getEmailCode(), user.getEmail());
        if (!result.isFlag()) {
            return result;
        }
        ArrayList<User> isExist = userDao.selectByEmail(user);
        if (!isExist.isEmpty()) {
            result.setFlag(false);
            result.setMsg("邮箱已被注册...");
            return result;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        Integer isSucceed = userDao.addUser(user);
        if (isSucceed != null && isSucceed != 0) {
            result.setMsg("注册成功！");
            result.setFlag(true);
        }
        return result;
    }

    @Override
    public ApiResult login(User user) {
        ArrayList<User> userLis = userDao.selectByEmail(user);
        ApiResult apiResult = new ApiResult();
        if (userLis.isEmpty()) {
            apiResult.setCode(Code.GET_ERR);
            apiResult.setMsg("账号不存在！");
        } else {
            User rightUser = userLis.get(0);
            boolean isSucceed = encoder.matches(user.getPassword(), rightUser.getPassword());
            apiResult.setCode(isSucceed ? Code.GET_OK : Code.GET_ERR);
            JSONObject data = new JSONObject();
            data.put("uId", rightUser.getuId());
            data.put("username", rightUser.getUsername());
            data.put("email", rightUser.getEmail());
            apiResult.setData(data);
            apiResult.setMsg(isSucceed ? "登录成功！" : "密码错误！");
        }
        return apiResult;
    }

    public TempResult updatePassword(User user) {
        TempResult tempResult = emailUtils.emailCheck(user.getEmailCode(), user.getEmail());
        if (tempResult.isFlag()) {
            user.setPassword(encoder.encode(user.getPassword()));
            tempResult.setMsg(userDao.updateUser(user) != 0 ? "修改成功！" : Code.ERROR_MSG);
        }
        return tempResult;
    }
}
