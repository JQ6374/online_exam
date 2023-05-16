package com.zz.Service;

import com.zz.bean.User;
import com.zz.utils.result.TempResult;

public interface UserService {
    TempResult register(User user);

    TempResult login(User user);

    TempResult updatePassword(User user);
}
