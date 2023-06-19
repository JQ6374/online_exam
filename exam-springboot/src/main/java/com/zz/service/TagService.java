package com.zz.service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Tag;
import com.zz.utils.result.ApiResult;

import java.util.List;

public interface TagService {
    ApiResult<Integer> updateTag(JSONObject tag);

    ApiResult<List<Tag>> selectAll(Integer uId);

    ApiResult<Integer> addTag(JSONObject json);

    ApiResult<Integer> deleteTag(Integer tagId);

}
