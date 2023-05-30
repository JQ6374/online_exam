package com.zz.Service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Tag;
import com.zz.utils.result.ApiResult;

public interface TagService {
    public ApiResult updateTag(JSONObject tag);

    public ApiResult selectAll(Integer uId);

    public ApiResult selectById(Integer tagId);

    public ApiResult addTag(JSONObject json);

    public ApiResult deleteTag(Integer tagId);

}
