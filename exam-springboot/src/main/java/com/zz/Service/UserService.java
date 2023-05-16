package com.zz.Service;

import com.zz.bean.User;
import com.zz.utils.result.TempResult;

public interface UserService {
    public TempResult register(User user);

    public TempResult login(User user);
}
