package com.zz.Service;

import com.zz.bean.Tag;
import com.zz.utils.result.ApiResult;

public interface TagService {
    public ApiResult updateTag(Tag tag);

    public ApiResult selectAll(Integer uId);

    public ApiResult selectById(Integer tagId);

    public ApiResult addTag(Tag tag);

    public Integer deleteTag(Integer tagId);
}
