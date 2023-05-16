package com.zz.Service.impl;

import com.zz.Service.UserService;
import com.zz.bean.User;
import com.zz.dao.UserDao;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import com.zz.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ValidateCodeUtils validateCodeUtils;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public TempResult register(User user) {
        TempResult result = validateCodeUtils.emailCheck(user.getEmailCode(), user.getEmail());
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
    public TempResult login(User user) {
        ArrayList<User> userLis = userDao.selectByEmail(user);
        TempResult tempResult = new TempResult();
        if (userLis.isEmpty()) {
            tempResult.setFlag(false);
            tempResult.setMsg("账号不存在！");
        } else {
            User rightUser = userLis.get(0);
            boolean isSucceed = encoder.matches(user.getPassword(), rightUser.getPassword());
            tempResult.setFlag(isSucceed);
            tempResult.setMsg(isSucceed ? "登录成功！" : "密码错误！");
        }
        return tempResult;
    }

    public TempResult updatePassword(User user) {
        TempResult tempResult = validateCodeUtils.emailCheck(user.getEmailCode(), user.getEmail());
        if (tempResult.isFlag()) {
            user.setPassword(encoder.encode(user.getPassword()));
            tempResult.setMsg(userDao.updateUser(user) != 0 ? "修改成功！" : "修改失败！");
        }
        return tempResult;
    }
}
