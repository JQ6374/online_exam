package com.zz.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.TagService;
import com.zz.bean.Tag;
import com.zz.dao.TagDao;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    public ApiResult<Integer> updateTag(JSONObject tag) {
        Integer integer = tagDao.updateTag(tag);
        return new ApiResult<>(Code.UPDATE_OK, null, integer == 1 ? "修改成功" : "修改失败");
    }

    @Override
    public ApiResult<List<Tag>> selectAll(Integer uId) {
        ArrayList<Tag> tags = tagDao.selectAll(uId);
        return new ApiResult<>(Code.GET_OK, tags, tags.size() > 0 ? "查询成功" : "查询失败");
    }

    @Override
    public ApiResult<Integer> addTag(JSONObject json) {
        Integer integer = tagDao.addTag(json);
        return new ApiResult<>(Code.UPDATE_OK, integer, integer != 0 ? "标签添加成功" : "标签添加失败");
    }

    @Override
    public ApiResult<Integer> deleteTag(Integer tagId) {
        Integer integer = tagDao.deleteTag(tagId);
        return new ApiResult<>(Code.UPDATE_OK, null, integer > 0 ? "标签删除成功" : "标签删除失败");

    }
}
