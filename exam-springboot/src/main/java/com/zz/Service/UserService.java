package com.zz.Service;

import com.zz.bean.User;
import com.zz.utils.TempResult;

public interface UserService {
    public TempResult register(User user, String code);

//    public boolean login();
}
