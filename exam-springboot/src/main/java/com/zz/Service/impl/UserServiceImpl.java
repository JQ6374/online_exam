package com.zz.Service.impl;

import com.zz.Service.UserService;
import com.zz.bean.User;
import com.zz.dao.UserDao;
import com.zz.utils.TempResult;
import com.zz.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ValidateCodeUtils validateCodeUtils;

    @Autowired
    private UserDao userDao;

    @Override
    public TempResult register(User user, String code) {
        TempResult result = validateCodeUtils.check(code, user.getEmail());
        if (!result.isFlag()) {
            return result;
        }
        Integer integer = userDao.addUser(user);
        if (integer != null && integer != 0) {
            result.setMsg("注册成功！");
            result.setFlag(true);
        }
        return result;
    }
}
