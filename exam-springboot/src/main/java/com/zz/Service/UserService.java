package com.zz.Service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.User;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;

public interface UserService {
    TempResult register(User user);

    ApiResult<JSONObject> login(User user);

    TempResult updatePassword(User user);
}
